package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String url = "jdbc:mysql://database-1.c0ye3b9zvrxd.eu-north-1.rds.amazonaws.com:3306/testdb";
    private static String name = "sergii";
    private static String password = "sergiibudin";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
