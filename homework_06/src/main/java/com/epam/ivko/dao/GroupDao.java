package com.epam.ivko.dao;

import com.epam.ivko.entity.Group;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupDao extends AbstractDao<Group> {

    @Override
    public void add(Group group) {
        Connection connection = getConnection();
        String insertTableSQL = "INSERT INTO groups"
                + "(name, curator, startDate) VALUES"
                + "(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getCurator());
            preparedStatement.setTimestamp(3, getTimeStamp(group.getStartDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public Group getById(int id) {
        Group result = null;
        Connection connection = getConnection();
        String selectTableSQL = "SELECT * FROM groups WHERE id = (?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(selectTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int groupId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String curator = resultSet.getString("curator");
                Date startDate = new Date(resultSet.getTimestamp("startDate").getTime());
                result = new Group(groupId, name, startDate, curator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return result;
    }

    @Override
    public List<Group> getAll() {
        List<Group> resultList = new ArrayList<>();
        Connection connection = getConnection();
        String selectAllTableSQL = "SELECT * FROM groups";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllTableSQL);
            while (resultSet.next()) {
                int groupId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String curator = resultSet.getString("curator");
                Date startDate = new Date(resultSet.getTimestamp("startDate").getTime());
                resultList.add(new Group(groupId, name, startDate, curator));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return resultList;
    }

    @Override
    public Group update(Group group) {
        Group updatedGroup = null;
        Connection connection = getConnection();
        String updateTableSQL = "UPDATE groups SET name = ?, curator = ?, startDate = ? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getCurator());
            preparedStatement.setTimestamp(3, getTimeStamp(group.getStartDate()));
            preparedStatement.setInt(4, group.getId());
            preparedStatement.executeUpdate();
            updatedGroup = getById(group.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return updatedGroup;
    }

    @Override
    public void remove(Group group) {
        removeById(group.getId());
    }

    @Override
    public void removeById(int id) {
        Connection connection = getConnection();
        String deleteFromTableSQL = "DELETE FROM groups WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(deleteFromTableSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void removeAll() {
        Connection connection = getConnection();
        String removeAllTableSQL = "DELETE FROM groups";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(removeAllTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public static void main(String[] args) throws ParseException {
        GroupDao dao = new GroupDao();
        System.out.println(dao.getById(1));
//        System.out.println(dao.getById(2));
        dao.add(new Group(1, "nnn", new Date(), "mmm"));
        Group group = dao.getById(1);
        group.setName("WWW");
        group.setCurator("BU-RA-TI-NO");
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        Date date = format.parse("17-07-2018");
        group.setStartDate(date);
        Group updatedGroup = dao.update(group);
        System.out.println(updatedGroup);
//        dao.remove(updatedGroup);
//        dao.removeAll();
    }
}