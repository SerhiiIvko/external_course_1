package com.epam.ivko.dao;

import com.epam.ivko.entity.Group;
import com.epam.ivko.entity.Student;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao extends AbstractDao<Student> {

    private GroupDao groupDao;

    public StudentDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void add(Student student) {
        Connection connection = getConnection();
        String insertTableSQL = "INSERT INTO students"
                + "(name, surname, birthday, group_id) VALUES"
                + "(?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setTimestamp(3, getTimeStamp(student.getBirthday()));
            preparedStatement.setInt(4, student.getGroup().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public Student getById(int id) {
        Student result = null;
        Connection connection = getConnection();
        String selectTableSQL = "SELECT * FROM students WHERE id = (?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(selectTableSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthday = new Date(resultSet.getTimestamp("birthday").getTime());
                int groupId = resultSet.getInt("group_id");
                Group group = groupDao.getById(groupId);
                result = new Student(studentId, name, surname, birthday, group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return result;
    }

    @Override
    public List<Student> getAll() {
        List<Student> resultList = new ArrayList<>();
        Connection connection = getConnection();
        String selectAllTableSQL = "SELECT * FROM students";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllTableSQL);
            while (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date birthday = new Date(resultSet.getTimestamp("birthday").getTime());
                int groupId = resultSet.getInt("group_id");
                Group group = groupDao.getById(groupId);
                resultList.add(new Student(studentId, name, surname, birthday, group));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return resultList;
    }

    @Override
    public Student update(Student student) {
        Student updatedStudent = null;
        Connection connection = getConnection();
        String updateTableSQL = "UPDATE students SET name = ?, surname = ?, birthday = ?, group_id = ? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setTimestamp(3, getTimeStamp(student.getBirthday()));
            preparedStatement.setInt(4, student.getGroup().getId());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.executeUpdate();
            updatedStudent = getById(student.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return updatedStudent;
    }

    @Override
    public void remove(Student student) {
        removeById(student.getId());
    }

    @Override
    public void removeById(int id) {
        Connection connection = getConnection();
        String deleteFromTableSQL = "DELETE FROM students WHERE id = ?";
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
        String removeAllTableSQL = "DELETE FROM students";
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
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        Date date = format.parse("17-07-2018");
        GroupDao dao = new GroupDao();
        Group group = dao.getById(1);
        StudentDao studentDao = new StudentDao(dao);
        studentDao.add(new Student(1, "xxx", "yyy", date, group));
        System.out.println(studentDao.getById(1));
        Student student = studentDao.getById(1);
//        System.out.println(dao.getById(2));
//        dao.add(new Group(2, "nnn", new Date(), "mmm"));
        student.setName("WWW");
        student.setSurname("BU-RA-TI-NO");
        student.setBirthday(date);
        student.setGroup(group);
//        Group updatedGroup = dao.update(group);
        Student updatedStudent = studentDao.update(student);
        System.out.println(updatedStudent);
        studentDao.remove(updatedStudent);
        studentDao.removeAll();
    }
}