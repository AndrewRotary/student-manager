package com.practica.dao;

import com.practica.domain.LibrarySubscription;
import com.practica.domain.Status;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by student on 2/8/2017.
 */
public class LibrarySubscriptionDao {
    private PreparedStatement preparedStatement = null;

    public LibrarySubscriptionDao() {
        //standart constructor needed
    }

    public ArrayList<LibrarySubscription> getAll() throws SQLException {
        ArrayList<LibrarySubscription> librarySubscriptions = null;
        preparedStatement = Settings.getConnection().prepareStatement("Select * from library_subscription");
        ResultSet rs = preparedStatement.executeQuery();
        librarySubscriptions = new ArrayList<LibrarySubscription>(rs.getFetchSize());
        while (rs.next()) {
            LibrarySubscription librarySubscription = new LibrarySubscription();
            librarySubscription.setId(rs.getLong("id_library_subscription"));
            librarySubscription.setStartDate(rs.getDate("start_date"));
            librarySubscription.setEndDate(rs.getDate("end_date"));
            librarySubscription.setStatus(Status.valueOf(rs.getString("status")));
            librarySubscriptions.add(librarySubscription);
        }
        return librarySubscriptions;
    }

    public void update(LibrarySubscription librarySubscription) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("UPDATE library_subscription set start_date = ?, end_date = ?, status = ? WHERE  id_library_subscription = ?;");
        preparedStatement.setDate(1, librarySubscription.getStartDate());
        preparedStatement.setDate(2, librarySubscription.getEndDate());
        preparedStatement.setString(3, String.valueOf(librarySubscription.getStatus()));
        preparedStatement.setLong(4, librarySubscription.getId());
        preparedStatement.executeUpdate();
    }

    public long create(LibrarySubscription librarySubscription) throws ParseException, SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into library_subscription (start_date, end_date, status) values(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, librarySubscription.getStartDate());
        preparedStatement.setDate(2, librarySubscription.getEndDate());
        preparedStatement.setString(3, String.valueOf(librarySubscription.getStatus()));
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        return keyId;
    }

    public LibrarySubscription findById(int id) throws SQLException {
        LibrarySubscription librarySubscription = new LibrarySubscription();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from library_subscription where id_library_subscription = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        librarySubscription.setId((long) rs.getInt("id_library_subscription"));
        librarySubscription.setStartDate(rs.getDate("start_date"));
        librarySubscription.setEndDate(rs.getDate("end_date"));
        librarySubscription.setStatus(Status.valueOf(rs.getString("status")));
        return librarySubscription;
    }
}
