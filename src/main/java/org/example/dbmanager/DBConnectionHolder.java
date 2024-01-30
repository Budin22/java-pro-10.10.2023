package org.example.dbmanager;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionHolder implements AutoCloseable {
    private static DBConnectionHolder instance;
    private final static String urlForConnection = "jdbc:h2:mem:default";
    private final static String driverClassName = "org.h2.Driver";
    private final Connection connection;

    private DBConnectionHolder() throws SQLException {

        PoolProperties p = new PoolProperties();
        p.setUrl(urlForConnection);
        p.setDriverClassName(driverClassName);
        p.setJmxEnabled(true);
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);

        connection = datasource.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnectionHolder getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnectionHolder();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnectionHolder();
        }
        return instance;
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
