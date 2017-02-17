package com.practica.dao;

import com.practica.domain.LibrarySubscription;
import com.practica.domain.Person;
import com.practica.domain.Phone;
import com.practica.domain.Status;
import connections.Settings;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/10/2017.
 */
public class PersonDao {
    private PreparedStatement preparedStatement = null;

    public PersonDao() {
        //standart constructor nedeed
    }

    public long create(Person person) throws SQLException, ParseException {
        long keyId = 0;
        AddressDao addressDao = new AddressDao();
        StudentDao studentDao = new StudentDao();
        LibrarySubscriptionDao librarySubscriptionDao = new LibrarySubscriptionDao();
        Phone phone = new Phone();
        PhoneDao phoneDao = new PhoneDao();
        Settings.getConnection().setAutoCommit(false);
        preparedStatement = Settings.getConnection().prepareStatement("Insert into person (first_name, last_name, gender, id_address, dob, id_student, id_profesor, id_library_subscription) values(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getGender());
        preparedStatement.setLong(4, addressDao.create(person.getAddress()));
        preparedStatement.setDate(5, (Date) person.getDob());
        preparedStatement.setLong(6, studentDao.create(person.getStudent()));
        preparedStatement.setNull(7, 0);
        preparedStatement.setLong(8, librarySubscriptionDao.create(person.getLibrarySubscription() == null ? new LibrarySubscription(Status.NONE) : person.getLibrarySubscription()));
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        person.setId(keyId);
        phone.setPerson(person);
        ArrayList<Phone> phones = (ArrayList<Phone>) person.getPhones();
        for (int i = 0; i < phones.size(); i++) {
            phones.get(i).setPerson(person);
            phoneDao.create(phones.get(i));
        }
        Settings.getConnection().commit();
        return keyId;
    }

    public void update(Person person) throws SQLException {

        AddressDao addressDao = new AddressDao();
        StudentDao studentDao = new StudentDao();
        PhoneDao phoneDao = new PhoneDao();
        Settings.getConnection().setAutoCommit(false);
        preparedStatement = Settings.getConnection().prepareStatement("UPDATE person SET first_name = ?, last_name = ?, gender = ?, picture_path = ?, dob = ? WHERE id_person = ?", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getGender());
        preparedStatement.setString(4, person.getPath());
        preparedStatement.setDate(5, (Date) person.getDob());
        preparedStatement.setLong(6, person.getId());
        addressDao.update(person.getAddress());
        studentDao.update(person.getStudent());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        List<Phone> phones = (List<Phone>) person.getPhones();
        for (int i = 0; i < phones.size(); i++) {
            phoneDao.update(phones.get(i));
        }
        Settings.getConnection().commit();
    }

    public long findByStudentId(long id) throws SQLException {
        long idPerson = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Select * from student, person  where student.id_student = person.id_student and student.id_student = ?");
        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        idPerson = rs.getLong("id_person");
        return idPerson;
    }
}
