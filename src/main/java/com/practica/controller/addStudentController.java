package com.practica.controller;

import com.practica.dao.GroupDao;
import com.practica.dao.PersonDao;
import com.practica.domain.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by student on 2/13/2017.
 */
@MultipartConfig
@WebServlet(name = "addStudentController")
public class addStudentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Phone phone = new Phone();
        Person person = new Person();
        Student student = new Student();
        Address address = new Address();
        address.setAddress(request.getParameter("address"));
        address.setCity(request.getParameter("city"));
        address.setCountry("country");
        person.setAddress(address);
        GroupDao groupDao = new GroupDao();
        Group group = null;
        try {
            group = groupDao.findById(Integer.parseInt(request.getParameter("group")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setGroup(group);
        student.setCalculateScholarship((double) 0);
        // obtains the upload file part in this multipart request
        final String path = "C:/Users/student/IdeaProjects/university-manager/src/main/webapp/resources/images";
        final Part filePart = request.getPart("file");
        final PrintWriter writer = response.getWriter();
        long millis = System.currentTimeMillis() % 1000;
        OutputStream out = new FileOutputStream(new File(path + File.separator + millis + ".jpg"));
        InputStream filecontent = filePart.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
        writer.println("New file " + millis + ".jpg" + " created at " + path);
        student.setImageAddress(millis + ".jpg");
        person.setStudent(student);
        person.setGender(request.getParameter("gender"));
        person.setFirstName(request.getParameter("firstName"));
        person.setLastName(request.getParameter("lastName"));
        person.setDob(Date.valueOf(request.getParameter("dob")));
        phone.setPhoneType(PhoneType.valueOf(request.getParameter("phoneType")));
        phone.setNumber(Integer.valueOf(request.getParameter("phone")));
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.add(phone);
        person.setPhones(phones);

        PersonDao personDao = new PersonDao();
        try {
            personDao.create(person);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/StudentController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        Student student = new Student();
        Address address = new Address();
        GroupDao groupDao = new GroupDao();
        person.setAddress(address);
        ArrayList<Group> groups = null;
        try {
            groups = (ArrayList<Group>) groupDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        person.setStudent(student);
        request.setAttribute("person", person);
        request.setAttribute("groups", groups);
        request.setAttribute("phoneType", PhoneType.values());
        RequestDispatcher rd = request.getRequestDispatcher("/addStudent.jsp");
        rd.forward(request, response);

    }
}
