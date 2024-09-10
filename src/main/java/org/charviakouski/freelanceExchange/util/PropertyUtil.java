package org.charviakouski.freelanceExchange.util;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Component
public class PropertyUtil {

    private static final String PROPERTY_FILE = "application.properties";

    @SneakyThrows
    public String getProperty(String propertyName) {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(Thread.currentThread().getContextClassLoader().getResource(PROPERTY_FILE).toURI())));
        return properties.getProperty(propertyName);
    }
}
