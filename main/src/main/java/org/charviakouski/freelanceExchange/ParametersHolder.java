package org.charviakouski.freelanceExchange;

import org.charviakouski.freelanceExchange.annotation.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
public class ParametersHolder {
    private static final String DATABASE_PROPERTIES_FILE = "application.properties";
    private static final String PROPERTY_NAME = "my.param.db";

    private String someText = "hfue";

    public ParametersHolder() {
    }

    public void getSomeText() throws FileNotFoundException {
        String path = ClassLoader.getSystemClassLoader().
                getResource(DATABASE_PROPERTIES_FILE).
                getPath();
        Map<String, String> propertiesMap = new BufferedReader(new FileReader(path)).
                lines().
                map(property -> property.split("=")).
                collect(toMap(arr -> arr[0], arr -> arr[1]));
        this.someText = propertiesMap.get(PROPERTY_NAME);
        System.out.println(someText);
    }
}
