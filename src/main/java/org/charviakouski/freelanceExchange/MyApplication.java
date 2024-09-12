package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Transactional;
import org.charviakouski.freelanceExchange.config.JavaConfig;
import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.charviakouski.freelanceExchange.controller.TaskController;
import org.charviakouski.freelanceExchange.exception.ControllerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class MyApplication {
    @SneakyThrows
    @Transactional
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        TaskController taskController = context.getBean(TaskController.class);
        String find = taskController.getAll();
        System.out.println(find);
        context.getBean(ConnectionHolder.class).destroyPool();
        context.close();



    }
}










