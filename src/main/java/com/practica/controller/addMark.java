package com.practica.controller;

import com.practica.dao.DisciplineDao;
import com.practica.dao.MarksDao;
import com.practica.dao.ProfessorDao;
import com.practica.domain.Discipline;
import com.practica.domain.Mark;
import com.practica.domain.Professor;
import com.practica.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/15/2017.
 */
@WebServlet(name = "addMark")
public class addMark extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarksDao marksDao = new MarksDao();
        Mark mark = new Mark();
        Discipline discipline = new Discipline();
        discipline.setId(Long.valueOf(request.getParameter("discipline")));
        Professor professor = new Professor();
        professor.setId(Long.valueOf(request.getParameter("professors")));
        Student student = new Student();
        student.setId(Long.valueOf(request.getParameter("id")));
        mark.setDiscipline(discipline);
        mark.setProfessor(professor);
        mark.setStudent(student);
        mark.setMark(Double.parseDouble(request.getParameter("mark")));
        try {
            marksDao.create(mark);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/StudentController");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Student student = new Student();
        student.setId(id);
        DisciplineDao disciplineDao = new DisciplineDao();
        ProfessorDao professorDao = new ProfessorDao();
        List<Discipline> disciplines = null;
        try {
            disciplines = disciplineDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Professor> professors = new ArrayList<Professor>();
        try {
            professors = professorDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("disciplines", disciplines);
        request.setAttribute("professors", professors);
        request.setAttribute("student", student);
        RequestDispatcher rd = request.getRequestDispatcher("/addMark.jsp");
        rd.forward(request, response);
    }
}
