package com.practica.dao;

import com.practica.domain.Discipline;
import com.practica.domain.Mark;
import connections.Settings;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by student on 2/9/2017.
 */
public class MarksDao {
    DisciplineDao disciplineDao = new DisciplineDao();
//    ProfessorDao professorDao = new ProfessorDao();

    private PreparedStatement preparedStatement = null;

    public MarksDao() {
        //Standrt constructor needed
    }

    public Mark findById(int id) throws SQLException {
        Mark mark = null;
        preparedStatement = Settings.getConnection().prepareStatement("Select * from mark where id_mark = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            mark = new Mark();
            mark.setId((long) rs.getInt("id_mark"));
            mark.setDiscipline(disciplineDao.findById(rs.getInt("id_discipline")));
            mark.setCreatedDate(rs.getDate("created_date"));
            mark.setMark(rs.getDouble("mark"));
        }
        return mark;
    }

    public ArrayList<Mark> findAll() {
        ArrayList<Mark> marks = null;
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from mark");
            ResultSet rs = preparedStatement.executeQuery();
            marks = new ArrayList<Mark>(rs.getFetchSize());
            while (rs.next()) {
                Mark mark = new Mark();
                mark.setId(rs.getLong("id_mark"));
                mark.setDiscipline(disciplineDao.findById(rs.getInt("id_discipline")));
//            mark.setProfessor(professorDao.findById(rs.getInt("id_proffessor")));
                mark.setCreatedDate(rs.getDate("created_date"));
                mark.setMark(rs.getDouble("mark"));
//            mark.setStudent();
                marks.add(mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }

    public ArrayList<Mark> getMarksByStudent(Long student_id) {
        ArrayList<Mark> marks = null;
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from mark WHERE id_student = ?");
            preparedStatement.setLong(1, student_id);
            ResultSet rs = preparedStatement.executeQuery();
            marks = new ArrayList<Mark>(rs.getFetchSize());
            while (rs.next()) {
                Mark mark = new Mark();
                mark.setId(rs.getLong("id_mark"));
                mark.setDiscipline(disciplineDao.findById(rs.getInt("id_discipline")));
                mark.setCreatedDate(rs.getDate("created_date"));
                mark.setMark(rs.getDouble("mark"));
                marks.add(mark);
//                System.out.println(mark.getDiscipline().getTitle() + "Nota " + mark.getMark());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }

    public ArrayList<Mark> getavgMarks(Long student_id) {
        ArrayList<Mark> marks = null;
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from average_mark WHERE id_student = ?");
            preparedStatement.setLong(1, student_id);
            ResultSet rs = preparedStatement.executeQuery();
            marks = new ArrayList<Mark>(rs.getFetchSize());
            while (rs.next()) {
                Mark mark = new Mark();
//                mark.setId(rs.getLong("id_mark"));
                Discipline discipline = new Discipline();
                discipline.setTitle(rs.getString("title"));
                mark.setDiscipline(discipline);
                mark.setMark(rs.getDouble("avg"));
                marks.add(mark);
//                System.out.println(mark.getDiscipline().getTitle() + "Nota " + mark.getMark());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }

    public long create(Mark mark) throws SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into mark (created_date, id_student, id_professor, id_discipline, mark) values(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, (Date) mark.getCreatedDate());
        preparedStatement.setLong(2, mark.getStudent().getId());
        preparedStatement.setLong(3, mark.getProfessor().getId());
        preparedStatement.setLong(4, mark.getDiscipline().getId());
        preparedStatement.setDouble(5, mark.getMark());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }

        return keyId;
    }
}
