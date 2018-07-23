package com.epam.ivko.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestDb {
    public static void main(String[] args) throws IOException {
        try {
            runTest();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }

    /**
     * Runs a test by creating a table, adding a value,
     * showing the table contents, and removing the table.
     */
    public static void runTest() throws SQLException, IOException {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World !')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next())
                    System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }

    /**
     * Gets a connection from the properties specified in the file
     * db.properties @return the database connection
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in =
                     TestDb.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(in);
        }
        String drivers = props.getProperty("db.driver");
        if (drivers != null) {
            System.setProperty("db.driver", drivers);
        }
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        return DriverManager.getConnection(url, username, password);
    }
}