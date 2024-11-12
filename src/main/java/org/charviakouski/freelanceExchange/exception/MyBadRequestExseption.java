package org.charviakouski.freelanceExchange.exception;

public class MyBadRequestExseption extends RuntimeException {

    public MyBadRequestExseption(String message) {
        super(message);
    }

    public MyBadRequestExseption(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBadRequestExseption(Throwable cause) {
        super(cause);
    }
}
