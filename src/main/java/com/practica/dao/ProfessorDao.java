package com.practica.dao;

import com.practica.domain.Person;
import com.practica.domain.Professor;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/15/2017.
 */
public class ProfessorDao {
    private PreparedStatement preparedStatement = null;

    public ProfessorDao() {
    }

    public List<Professor> getAll() throws SQLException {
        List<Professor> professors = new ArrayList<Professor>();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from profesor, person  where profesor.id_profesor = person.id_profesor");
        ResultSet rs = preparedStatement.executeQuery();
        AddressDao addressDao = new AddressDao();
        while (rs.next()) {
            Professor professor = new Professor();
            Person person = new Person();
            professor.setId(rs.getLong("id_profesor"));
            professor.setDob(rs.getDate("dob"));
            professor.setFirstName(rs.getString("first_name"));
            professor.setLastName(rs.getString("last_name"));
            professor.setGender(rs.getString("gender"));
            professors.add(professor);
        }

        return professors;
    }
}
