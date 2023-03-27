package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://@localhost:3306/mydbtest";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_PASSWORD, DB_USERNAME);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
        }
        return connection;
    }

}
