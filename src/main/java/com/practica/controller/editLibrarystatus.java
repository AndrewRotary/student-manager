package com.practica.controller;

import com.practica.dao.LibrarySubscriptionDao;
import com.practica.dao.StudentDao;
import com.practica.domain.LibrarySubscription;
import com.practica.domain.Status;
import com.practica.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by student on 2/14/2017.
 */
@WebServlet(name = "editLibrarystatus")
public class editLibrarystatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LibrarySubscription librarySubscription = new LibrarySubscription();
        LibrarySubscriptionDao librarySubscriptionDao = new LibrarySubscriptionDao();
        librarySubscription.setId(Long.valueOf(request.getParameter("id")));
        librarySubscription.setStatus(Status.valueOf(request.getParameter("status")));
        librarySubscription.setStartDate(Date.valueOf(request.getParameter("startDate")));
        librarySubscription.setEndDate(Date.valueOf(request.getParameter("endDate")));
        try {
            librarySubscriptionDao.update(librarySubscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/StudentController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentDao studentDao = new StudentDao();
        Student student = null;
        try {
            student = studentDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("statuses", Status.values());
        request.setAttribute("student", student);
        RequestDispatcher rd = request.getRequestDispatcher("/editLibrarystatus.jsp");
        rd.forward(request, response);
    }
}
