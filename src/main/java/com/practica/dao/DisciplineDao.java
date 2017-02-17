package com.practica.dao;

import com.practica.domain.Discipline;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by student on 2/8/2017.
 */
public class DisciplineDao {
    private PreparedStatement preparedStatement = null;

    public DisciplineDao() {
        // Default constructor needed
    }

    ;

    public Discipline findById(int id) throws SQLException {
        Discipline discipline = new Discipline();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from discipline where id_discipline = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        discipline.setId((long) rs.getInt("id_discipline"));
        discipline.setScholarshipThreshold(rs.getDouble("scholarship_threshold"));
        discipline.setTitle(rs.getString("title"));

        return discipline;
    }

    public ArrayList<Discipline> getAll() throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("Select * from discipline");
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>(rs.getFetchSize());
        while (rs.next()) {
            Discipline discipline = new Discipline();
            discipline.setId(rs.getLong("id_discipline"));
            discipline.setScholarshipThreshold(rs.getDouble("scholarship_threshold"));
            discipline.setTitle(rs.getString("title"));
            disciplines.add(discipline);
        }
        return disciplines;
    }

}
