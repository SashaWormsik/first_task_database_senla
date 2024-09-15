package org.charviakouski.freelanceExchange.aspect;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class TransactionalAspect {
    @Autowired
    private ConnectionHolder connectionHolder;

    @SneakyThrows
    @Around(value = "@annotation(org.charviakouski.freelanceExchange.annotation.Transactional)")
    public Object advice(ProceedingJoinPoint joinPoint) {
        Connection connection = connectionHolder.getConnection();
        connectionHolder.setTransactionStatus(true);
        boolean previousAutoCommit = false;
        try {
            previousAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            Object result = joinPoint.proceed();
            connection.commit();
            return result;
        } catch (RuntimeException runtimeException) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при откате транзакции", e);
            }
            throw runtimeException;
        } finally {
            try {
                connection.setAutoCommit(previousAutoCommit);
                connectionHolder.setTransactionStatus(false);
                connectionHolder.releaseConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при задании автокомита", e);
            }
        }
    }
}