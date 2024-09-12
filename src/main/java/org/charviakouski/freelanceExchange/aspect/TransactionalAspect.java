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

@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class TransactionalAspect {
    private ConnectionHolder connectionHolder;

    @Autowired
    public TransactionalAspect(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }


    @SneakyThrows
    @Around(value = "@annotation(org.charviakouski.freelanceExchange.annotation.Transactional)")
    public Object advice(ProceedingJoinPoint joinPoint) {
        try {
            connectionHolder.getConnection().setAutoCommit(false);
            System.out.println("ТРАНЗАКЦИЯ ОТКРЫТА!!!!!!!!!!!!!==============");
            Object result = joinPoint.proceed();
            connectionHolder.getConnection().commit();
            connectionHolder.getConnection().setAutoCommit(true);
            System.out.println("КОМИТ!!!!!!!!!!!!!==============");
            return result;
        } catch (RuntimeException e) {
            connectionHolder.getConnection().rollback();
            connectionHolder.getConnection().setAutoCommit(true);
            System.out.println("ОТКАТ!!!!!!!!!!!!!==============");
            throw e;
        }
    }
}




