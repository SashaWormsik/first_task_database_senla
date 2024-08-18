package org.charviakouski.freelanceExchange;

public class Main {
    @lombok.SneakyThrows
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext("org.charviakouski.freelanceExchange");
        UserController userController = applicationContext.getInstance(UserController.class);
        userController.execute();
    }
}




