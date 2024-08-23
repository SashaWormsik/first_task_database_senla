package org.charviakouski.freelanceExchange.util;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyLoader {
    @SneakyThrows
    public static String getProperty(String propertyFileName, String propertyName) {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(Thread.currentThread().getContextClassLoader().getResource(propertyFileName).toURI())));
        return properties.getProperty(propertyName);
    }
}
