package org.charviakouski.freelanceExchange;

public class ApplicationRunner {

    public static ApplicationContext run(Class<?> application) {
        return new ApplicationContext(application);
    }
}
