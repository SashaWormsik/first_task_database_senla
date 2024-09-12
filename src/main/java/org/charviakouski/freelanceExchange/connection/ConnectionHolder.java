package org.charviakouski.freelanceExchange.connection;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

@Component
public class ConnectionHolder {

    private final HashMap<String, ProxyConnection> connections = new HashMap<>();

    public ProxyConnection getConnection() {
        return connections.computeIfAbsent(Thread.currentThread().getName(), ConnectionHolder::apply);
    }

    private static ProxyConnection apply(String connection) {
        try {
            Connection tempConnection = ConnectionFactory.getConnection();
            return new ProxyConnection(tempConnection);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public void destroyPool() {
        connections.values().forEach(connection -> {
            try {
                connection.reallyClose();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
