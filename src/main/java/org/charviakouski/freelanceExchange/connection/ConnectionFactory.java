package org.charviakouski.freelanceExchange.connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Component
public class ConnectionFactory {
    private static final String DATABASE_PROPERTIES_FILE = "application";
    private static final String USER_PROPERTY = "username";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String URL_PROPERTY = "url";
    private static final String DRIVER_PROPERTY = "driver";
    private static final String USER;
    private static final String PASSWORD;
    private static final String URL;
    private static final String DRIVER;


    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTIES_FILE);
        if (resourceBundle.containsKey(USER_PROPERTY)) {
            USER = resourceBundle.getString(USER_PROPERTY);
        } else {
            throw new RuntimeException("Error reading the value of the \"USER\" property from the settings file");
        }
        if (resourceBundle.containsKey(PASSWORD_PROPERTY)) {
            PASSWORD = resourceBundle.getString(PASSWORD_PROPERTY);
        } else {
            throw new RuntimeException("Error reading the value of the \"PASSWORD\" property from the settings file");
        }
        if (resourceBundle.containsKey(URL_PROPERTY)) {
            URL = resourceBundle.getString(URL_PROPERTY);
        } else {
            throw new RuntimeException("Error reading the value of the \"URL\" property from the settings file");
        }
        if (resourceBundle.containsKey(DRIVER_PROPERTY)) {
            DRIVER = resourceBundle.getString(DRIVER_PROPERTY);
        } else {
            throw new RuntimeException("Error reading the value of the \"DRIVER\" property from the settings file");
        }
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException("Driver " + DRIVER + " wasn't found: ", exception);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

