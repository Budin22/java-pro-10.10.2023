package org.example.di;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements Factory<Connection> {
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
    @Override
    public Connection provide() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final String url = System.getenv("MYSQL_URL");
            final String user = System.getenv("MYSQL_USER");
            final String password = System.getenv("MYSQL_PASSWORD");

            final Connection connection
                    = DriverManager.getConnection(url, user, password);
            logger.debug("debug");
            logger.info("connection created");

            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
