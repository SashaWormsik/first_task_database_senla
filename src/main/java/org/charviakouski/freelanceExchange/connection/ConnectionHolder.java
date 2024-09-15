package org.charviakouski.freelanceExchange.connection;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

@Component
@Data
public class ConnectionHolder {

    @Autowired
    private ConnectionFactory connectionFactory;
    private final ThreadLocal<Boolean> transactionStatus = ThreadLocal.withInitial(() -> false);
    private final Map<Thread, Connection> threadConnectionMap = new ConcurrentHashMap<>();
    private final BlockingQueue<Connection> connectionPool = new LinkedBlockingDeque<>();
    ;

    public Connection getConnection() throws SQLException {
        Thread currentThread = Thread.currentThread();
        if (threadConnectionMap.containsKey(currentThread) && isTransactionActive()) {
            if (threadConnectionMap.get(currentThread).isClosed()) {
                throw new RuntimeException("Connection используемый в транзакции закрыт");
            }
            return threadConnectionMap.get(currentThread);
        }
        return getConnectionFromPool();
    }

    public void releaseConnection() {
        Thread currentThread = Thread.currentThread();
        Connection connection = threadConnectionMap.get(currentThread);
        try {
            if (connection != null && !connection.isClosed() && !isTransactionActive()) {
                connectionPool.add(threadConnectionMap.remove(currentThread));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка освобождения connection");
        }
    }


    public boolean isTransactionActive() {
        return transactionStatus.get();
    }

    public void setTransactionStatus(boolean status) {
        transactionStatus.set(status);
    }

    @PreDestroy
    public void closeAllConnections() {
        connectionPool.addAll(threadConnectionMap.values());
        for (Connection connection : connectionPool) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException("Ошибка закрытия всех connections", e);
                }
            }
        }
        threadConnectionMap.clear();
        connectionPool.clear();
    }

    private Connection getConnectionFromPool() throws SQLException {
        if (connectionPool.isEmpty()) {
            threadConnectionMap.put(Thread.currentThread(), connectionFactory.getConnection());
        } else {
            for (Connection connection : connectionPool) {
                if (connection != null && !connection.isClosed()) {
                    threadConnectionMap.put(Thread.currentThread(), connection);
                    break;
                }
            }
        }
        return threadConnectionMap.get(Thread.currentThread());
    }
}
