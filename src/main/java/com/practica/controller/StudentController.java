package com.practica.controller;

import com.practica.dao.StudentDao;
import com.practica.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by student on 2/9/2017.
 */

@WebServlet(name = "StudentController")
public class StudentController extends HttpServlet {
    StudentDao studentDao = new StudentDao();
    List<Student> students = null;

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
        try {
            students = studentDao.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("students", students);
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/studentList.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }
    }
}
