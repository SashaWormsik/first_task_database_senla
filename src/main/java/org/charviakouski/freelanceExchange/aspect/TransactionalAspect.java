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
        Object result;
        Connection connection = connectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
            result = joinPoint.proceed();
            connection.commit();
        } catch (RuntimeException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}