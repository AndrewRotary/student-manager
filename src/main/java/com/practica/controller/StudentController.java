package com.practica.controller;

import com.practica.dao.DisciplineDao;
import com.practica.dao.GroupDao;
import com.practica.dao.StudentDao;
import com.practica.domain.Discipline;
import com.practica.domain.Group;
import com.practica.domain.Student;
import com.practica.form.SearchForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by student on 2/9/2017.
 */

@WebServlet(name = "StudentController")
public class StudentController extends HttpServlet {
    StudentDao studentDao = new StudentDao();

    List<Student> students = null;
    List<Group> groups = null;
    List<Discipline> disciplines = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idStudents = request.getParameterMap().get("delete[]");
        for (int i = 0; i < idStudents.length; i++) {
            Student student = new Student();
            student.setId(Long.valueOf(idStudents[i]));
            try {
                studentDao.updateStatus(student);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        response.sendRedirect("/StudentController");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDao();
        DisciplineDao disciplineDao = new DisciplineDao();
        try {
            if (request.getParameter("search") != null) {
                SearchForm searchForm = new SearchForm();
                if (!request.getParameter("lastName").isEmpty()) {
                    searchForm.setName(request.getParameter("lastName"));
                }
                searchForm.setAddress(request.getParameter("address"));
                if (!request.getParameter("dobStart").isEmpty() && !request.getParameter("dobEnd").isEmpty()) {
                    searchForm.setBirthDate(Date.valueOf(request.getParameter("dobStart")));
                    searchForm.setEndDate(Date.valueOf(request.getParameter("dobEnd")));
                }
                if (!request.getParameter("gender").isEmpty()) {
                    searchForm.setGender(request.getParameter("gender"));
                }
                if (request.getParameter("discipline") != null) {
                    searchForm.setDisciplineId(Long.parseLong(request.getParameter("discipline")));
                }
                if (request.getParameter("group") != null) {
                    searchForm.setGroupId(Long.parseLong(request.getParameter("group")));
                }
                if (!request.getParameter("average").isEmpty()) {
                    searchForm.setTotalAverage(Double.parseDouble(request.getParameter("average")));
                }

                students = studentDao.getAllStudentsBySearch(searchForm);
                System.out.println("working");
            } else {
                students = studentDao.getAllStudents();
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            try {
                students = studentDao.getAllStudents();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            groups = groupDao.getAll();
            disciplines = disciplineDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("students", students);
        request.setAttribute("groups", groups);
        request.setAttribute("disciplines", disciplines);
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/studentList.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }
    }
}
