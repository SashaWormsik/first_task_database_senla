package org.charviakouski.freelanceExchange;

import org.charviakouski.freelanceExchange.controller.UserController;

import java.util.Map;

public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationRunner.run(MyApplication.class);
        Map<Class<?>, Object> beanMap = applicationContext.getBeanMap();
        beanMap.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
        applicationContext.getBean(UserController.class).execute();

    }
}










