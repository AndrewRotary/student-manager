package com.practica.dao;

import com.practica.domain.Student;
import com.practica.form.SearchForm;
import connections.Settings;
import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by student on 2/9/2017.
 */
public class StudentDao {

    GroupDao groupDao = new GroupDao();
    AddressDao addressDao = new AddressDao();
    PhoneDao phoneDao = new PhoneDao();
    LibrarySubscriptionDao librarySubscriptionDao = new LibrarySubscriptionDao();
    MarksDao marksDao = new MarksDao();
    private PreparedStatement preparedStatement = null;

    public StudentDao() {
        // Default constructor needed
    }

    public Student findById(int id) throws SQLException {
        Student student = new Student();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from student, person  where student.id_student = person.id_student and student.id_student = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            student.setId((long) id);
            student.setCalculateScholarship(rs.getDouble("calculate_scholarship"));
            student.setGroup(groupDao.findById(rs.getInt("id_group")));
            student.setDob(rs.getDate("dob"));
            student.setAddress(addressDao.findById(rs.getInt("id_address")));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setGender(rs.getString("gender"));
            student.setLibrarySubscription(librarySubscriptionDao.findById(rs.getInt("id_library_subscription")));
            student.setPhones(phoneDao.findAll((long) rs.getInt("id_person")));
            student.setMarks(marksDao.getMarksByStudent(student.getId()));
            student.setStatus(rs.getBoolean("status"));
            student.setImageAddress(rs.getString("image_name"));
        }
        return student;
    }

    public void update(Student student) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("UPDATE student set calculate_scholarship = ?, id_group = ?, image_name = ? WHERE id_student = ?");
        preparedStatement.setDouble(1, student.getCalculateScholarship());
        preparedStatement.setLong(2, groupDao.update(student.getGroup()));
        preparedStatement.setString(3, student.getImageAddress());
        preparedStatement.setLong(4, student.getId());
        preparedStatement.executeUpdate();

    }

    public void updateStatus(Student student) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("UPDATE student set status = ? WHERE id_student = ?");
        preparedStatement.setBoolean(1, false);
        preparedStatement.setLong(2, student.getId());
        preparedStatement.executeUpdate();
    }

    public List<Student> getAllStudentsBySearch(SearchForm searchForm) throws SQLException {
        List<Student> students = new ArrayList<Student>();
        String name = "";

        List<String> whereConditions = new ArrayList<String>();
        if (StringUtils.isNotEmpty(searchForm.getName())) {
            name = "person.first_name LIKE '" + searchForm.getName() + "%' or person.last_name LIKE '" + searchForm.getName() + "%'";
            whereConditions.add("first_name LIKE '" + name + "'");

        }

//        if()

        String whereClause = whereConditions.stream().collect(Collectors.joining(" AND "));
//        System.out.println(whereConditions);

        preparedStatement = Settings.getConnection().prepareStatement("Select * from student inner JOIN person on  student.id_student = person.id_student WHERE " + name);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id_student"));
            student.setCalculateScholarship(rs.getDouble("calculate_scholarship"));
            student.setGroup(groupDao.findById(rs.getInt("id_group")));
            student.setDob(rs.getDate("dob"));
            student.setAddress(addressDao.findById(rs.getInt("id_address")));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setGender(rs.getString("gender"));
            student.setLibrarySubscription(librarySubscriptionDao.findById(rs.getInt("id_library_subscription")));
            student.setPhones(phoneDao.findAll((long) rs.getInt("id_person")));
            student.setMarks(marksDao.getavgMarks(student.getId()));
            student.setStatus(rs.getBoolean("status"));
            student.setImageAddress(rs.getString("image_name"));
            if (student.isStatus()) {
                students.add(student);
            }
        }

        return students;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from student, person  where student.id_student = person.id_student");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id_student"));
            student.setCalculateScholarship(rs.getDouble("calculate_scholarship"));
            student.setGroup(groupDao.findById(rs.getInt("id_group")));
            student.setDob(rs.getDate("dob"));
            student.setAddress(addressDao.findById(rs.getInt("id_address")));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setGender(rs.getString("gender"));
            student.setLibrarySubscription(librarySubscriptionDao.findById(rs.getInt("id_library_subscription")));
            student.setPhones(phoneDao.findAll((long) rs.getInt("id_person")));
            student.setMarks(marksDao.getavgMarks(student.getId()));
            student.setStatus(rs.getBoolean("status"));
            student.setImageAddress(rs.getString("image_name"));
            if (student.isStatus()) {
                students.add(student);
            }
        }
        return students;
    }

    public long create(Student student) throws SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into student (calculate_scholarship, id_group,image_name, status ) values(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDouble(1, student.getCalculateScholarship());
        preparedStatement.setLong(2, groupDao.create(student.getGroup()));
        preparedStatement.setString(3, student.getImageAddress());
        preparedStatement.setBoolean(4, true);
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        return keyId;
    }


}
