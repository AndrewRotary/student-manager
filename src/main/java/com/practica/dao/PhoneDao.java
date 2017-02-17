package com.practica.dao;

import com.practica.domain.Phone;
import com.practica.domain.PhoneType;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/9/2017.
 */
public class PhoneDao {
    private PreparedStatement preparedStatement = null;

    public Phone findById(int id) {
        Phone phone = new Phone();
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from phone where id_phone =  ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            phone.setId(rs.getLong("id_phone"));
            phone.setNumber(rs.getInt("number"));
            phone.setPhoneType(PhoneType.valueOf(rs.getString(("phone_type"))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phone;
    }

    public List<Phone> findAll(Long id) {
        List<Phone> phones = null;
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from phone where id_person =  ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            phones = new ArrayList<Phone>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getLong("id_phone"));
                phone.setNumber(rs.getInt("number"));
                phone.setPhoneType(PhoneType.valueOf(rs.getString(("phone_type"))));
                phones.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    public long create(Phone phone) throws SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into phone (number, phone_type, id_person) values(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, phone.getNumber());
        preparedStatement.setString(2, String.valueOf(PhoneType.valueOf(String.valueOf(phone.getPhoneType()))));
        preparedStatement.setLong(3, phone.getPerson().getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        return keyId;
    }

    public void update(Phone phone) {
        try {
            preparedStatement = Settings.getConnection().prepareStatement("UPDATE phone set number = ?, phone_type = ? WHERE  id_phone = ?");
            preparedStatement.setInt(1, phone.getNumber());
            preparedStatement.setString(2, String.valueOf(phone.getPhoneType()));
            preparedStatement.setLong(3, phone.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
