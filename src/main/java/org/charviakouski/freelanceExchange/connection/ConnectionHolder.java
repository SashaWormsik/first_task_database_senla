package org.charviakouski.freelanceExchange.connection;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@Component
public class ConnectionHolder {

    private HashMap<String, Connection> connections = new HashMap<>();

    public Connection getConnection() {
        return connections.computeIfAbsent(Thread.currentThread().getName(), connection -> {
            try {
                return ConnectionFactory.getConnection();
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        });
    }
}
