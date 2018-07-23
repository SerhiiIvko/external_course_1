package com.epam.ivko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T> {
    private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String MYSQL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private Connection connection;
    private Properties connectionProperties;

    public AbstractDao() {
        connectionProperties = new Properties();
        connectionProperties.setProperty("user", USER);
        connectionProperties.setProperty("password", PASSWORD);
        connectionProperties.setProperty("useSSL", "false");
        connectionProperties.setProperty("autoReconnect", "true");
        connectionProperties.setProperty("useUnicode", "true");
        connectionProperties.setProperty("useJDBCCompliantTimezoneShift", "true");
        connectionProperties.setProperty("useLegacyDatetimeCode", "false");
        connectionProperties.setProperty("serverTimezone", "UTC");
    }

    protected Connection getConnection() {
        connection = null;
        try {
            Class.forName(MYSQL_DRIVER_NAME);
            connection = DriverManager.getConnection(URL, connectionProperties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void add(T entity);

    public abstract T getById(int id);

    public abstract List<T> getAll();

    public abstract T update(T entity);

    public abstract void remove(T entity);

    public abstract void removeById(int id);

    public abstract void removeAll();

    protected static Timestamp getTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }
}