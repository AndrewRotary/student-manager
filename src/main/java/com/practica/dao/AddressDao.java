package com.practica.dao;

import com.practica.domain.Address;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/7/2017.
 */
public class AddressDao {
    private PreparedStatement preparedStatement = null;

    public AddressDao() {
        // Default constructor needed
    }

    public List<Address> getAllAddresses() throws SQLException {
        ArrayList<Address> addresses = null;
        preparedStatement = Settings.getConnection().prepareStatement("Select * from address");
        ResultSet rs = preparedStatement.executeQuery();
        addresses = new ArrayList<Address>(rs.getFetchSize());
        while (rs.next()) {
            Address adr = new Address();
            adr.setCity(rs.getString("country"));
            adr.setCountry(rs.getString("city"));
            adr.setAddress(rs.getString("address"));
            adr.setId(rs.getLong("id"));
            addresses.add(adr);
        }
        return addresses;
    }

    public Address findById(int id) throws SQLException {
        Address address = new Address();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from address where id_address = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        address.setCity(rs.getString("city"));
        address.setCountry(rs.getString("country"));
        address.setAddress(rs.getString("address"));
        address.setId(rs.getLong("id_address"));

        return address;
    }

    public long create(Address address) throws SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into address (country, city, address) values(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getAddress());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        return keyId;
    }

    public void update(Address address) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("UPDATE address set country = ?, city = ?, address = ? WHERE  id_address = ?;");
        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setDouble(4, address.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("DELETE FROM address WHERE  id_address = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }
}
