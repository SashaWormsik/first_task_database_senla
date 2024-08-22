package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.ApplicationContext;
import org.charviakouski.freelanceExchange.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class InjectValueInFieldProcessor extends AbstractProcessor {

    private static final String DATABASE_PROPERTIES_FILE = "application.properties";
    private Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectValueInFieldProcessor() {
        String path = ClassLoader.getSystemClassLoader().
                getResource(DATABASE_PROPERTIES_FILE).
                getPath();
        this.propertiesMap = new BufferedReader(new FileReader(path)).
                lines().
                map(property -> property.split("=")).
                collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void process(Class<?> type, ApplicationContext context) {
        if (context.getBeanMap().containsKey(type)) {
            return;
        }
        Set<Field> annotationFields = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Value.class) != null)
                .collect(Collectors.toSet());
        if (annotationFields.isEmpty()) {
            return;
        }
        Object object = type.getConstructor().newInstance();
        for (Field annotationField : annotationFields) {
            Value annotation = annotationField.getAnnotation(Value.class);
            String propertyName = annotation.value();
            String propertyValue = propertiesMap.get(propertyName);
            annotationField.setAccessible(true);
            annotationField.set(object, propertyValue);
        }
        putInContext(object, context);
    }
}

