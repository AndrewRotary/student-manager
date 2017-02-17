package com.practica.dao;

import com.practica.domain.Group;
import connections.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2/8/2017.
 */
public class GroupDao {
    private PreparedStatement preparedStatement = null;

    public GroupDao() {
        //Standard constructor needed
    }

    public List<Group> getAll() throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("Select * from groupp");
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Group> groups = new ArrayList<Group>(rs.getFetchSize());
        while (rs.next()) {
            Group group = new Group();
            group.setName(rs.getString("name"));
            group.setId(rs.getLong("id_group"));
            groups.add(group);
        }

        return groups;
    }

    public Group findById(int id) throws SQLException {
        Group group = new Group();
        preparedStatement = Settings.getConnection().prepareStatement("Select * from groupp where id_group = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        group.setName(rs.getString("name"));
        group.setId(rs.getLong("id_group"));

        return group;
    }

    public long create(Group group) throws SQLException {
        long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement("Insert into groupp (name) values(?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, group.getName());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            keyId = rs.getLong(1);
        }
        return keyId;
    }

    public long update(Group group) throws SQLException {

        preparedStatement = Settings.getConnection().prepareStatement("UPDATE groupp set name = ? WHERE  id_group = ?;");
        preparedStatement.setString(1, group.getName());
        preparedStatement.setLong(2, group.getId());
        preparedStatement.executeUpdate();

        return group.getId();
    }

    public void delete(int id) throws SQLException {

        preparedStatement = Settings.getConnection().prepareStatement("DELETE FROM groupp WHERE  id_group = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

}
