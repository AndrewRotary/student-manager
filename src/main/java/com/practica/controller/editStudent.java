package com.practica.controller;

import com.practica.dao.GroupDao;
import com.practica.dao.PersonDao;
import com.practica.dao.PhoneDao;
import com.practica.dao.StudentDao;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/14/2017.
 */

@MultipartConfig
@WebServlet(name = "editStudent")
public class editStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        Student student = new Student();
        Address address = new Address();
        GroupDao groupDao = new GroupDao();
        address.setAddress(request.getParameter("address"));
        address.setCity(request.getParameter("city"));
        address.setCountry("country");
        address.setId(Long.valueOf(request.getParameter("idAddress")));
        person.setAddress(address);
        Group group = null;
        try {
            group = groupDao.findById(Integer.parseInt(request.getParameter("group")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setId(Long.valueOf(request.getParameter("id")));
        student.setGroup(group);
        student.setCalculateScholarship((double) 0);

        // obtains the upload file part in this multipart request
        final String path = "C:/Users/student/IdeaProjects/university-manager/src/main/webapp/resources/images";
        final Part filePart = request.getPart("file");
        if (filePart.getSize() > 0) {
            final PrintWriter writer = response.getWriter();
            OutputStream out = new FileOutputStream(new File(path + File.separator + student.getId() + ".jpg"));
            InputStream filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                writer.println("New file " + student.getId() + ".jpg" + " created at " + path);
                student.setImageAddress(student.getId() + ".jpg");
            }
            out.close();
        } else {
            student.setImageAddress(request.getParameter("image-root"));
        }
        person.setStudent(student);
        person.setGender(request.getParameter("gender"));
        person.setFirstName(request.getParameter("firstName"));
        person.setLastName(request.getParameter("lastName"));
        person.setDob(Date.valueOf(request.getParameter("dob")));
        ArrayList<Phone> phones = new ArrayList<Phone>();
        String[] idPhone = request.getParameterMap().get("id_phone[]");
        String[] phoneTypeArray = request.getParameterMap().get("phoneType[]");
        String[] phoneNumber = request.getParameterMap().get("phone[]");
        for (int i = 0; i < idPhone.length; i++) {
            Phone phone = new Phone();
            phone.setPhoneType(PhoneType.valueOf(phoneTypeArray[i]));
            phone.setNumber(Integer.valueOf(phoneNumber[i]));
            phone.setId(Long.valueOf(idPhone[i]));
            phone.setPerson(person);
            phones.add(phone);
        }
//        list = request.getParameter("id_phone")
        person.setPhones(phones);
        PersonDao personDao = new PersonDao();
        try {
            person.setId(personDao.findByStudentId(student.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            personDao.update(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/StudentController");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        GroupDao groupDao = new GroupDao();
        List<Group> groupList = null;
        try {
            groupList = groupDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StudentDao studentDao = new StudentDao();
        Student student = null;
        try {
            student = studentDao.findById((int) id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PhoneDao phoneDao = new PhoneDao();
        PersonDao personDao = new PersonDao();
        List<Phone> phones = null;
        try {
            phones = phoneDao.findAll(personDao.findByStudentId(student.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("phones", phones);
        request.setAttribute("groups", groupList);
        request.setAttribute("student", student);
        request.setAttribute("phoneType", PhoneType.values());
        RequestDispatcher rd = request.getRequestDispatcher("/editstudent.jsp");
        rd.forward(request, response);
    }

}
