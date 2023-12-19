package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String mysqlUrl = System.getenv("MYSQL_URL");
            String mysqlName = System.getenv("MYSQL_NAME");
            String mysqlPassword = System.getenv("MYSQL_PASSWORD");

            return DriverManager.getConnection(mysqlUrl, mysqlName, mysqlPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
