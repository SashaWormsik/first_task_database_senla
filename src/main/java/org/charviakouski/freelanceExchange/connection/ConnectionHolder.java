package org.charviakouski.freelanceExchange.connection;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

@Component
public class ConnectionHolder {

    @Autowired
    private ConnectionFactory connectionFactory;
    private BlockingQueue<ProxyConnection> freeConnection;
    private BlockingQueue<ProxyConnection> usedConnection;
    private final HashMap<String, ProxyConnection> transactionConnections = new HashMap<>();



    public ProxyConnection getConnection() {
        return transactionConnections.computeIfAbsent(Thread.currentThread().getName(), this::apply);
    }

    private ProxyConnection apply(String connection) {
        try {
            Connection tempConnection = connectionFactory.getConnection();
            return new ProxyConnection(tempConnection);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @PreDestroy
    public void destroyPool() {
        System.out.println("DESTROY==============================");
        transactionConnections.values().forEach(connection -> {
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
