package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.BeanFactory;
import org.charviakouski.freelanceExchange.annotation.Value;
import org.charviakouski.freelanceExchange.util.PropertyLoader;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InjectValueInFieldProcessor extends AbstractProcessor {

    private static final String DATABASE_PROPERTIES_FILE = "application.properties";


    @SneakyThrows
    public InjectValueInFieldProcessor() {
    }

    @Override
    @SneakyThrows
    public void process(Class<?> type, BeanFactory factory) {
        if (factory.getBeanMap().containsKey(type)) {
            return;
        }
        Set<Field> annotationFields = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Value.class) != null)
                .collect(Collectors.toSet());
        if (annotationFields.isEmpty()) {
            return;
        }
        Object object = type.getConstructor().newInstance();
        annotationFields.forEach(annotationField -> createObject(annotationField, object));
        putInContext(object, factory);
    }


    @SneakyThrows
    private void createObject(Field annotationField, Object object) {
        Value annotation = annotationField.getAnnotation(Value.class);
        String propertyName = annotation.value();
        String propertyValue = PropertyLoader.getProperty(DATABASE_PROPERTIES_FILE, propertyName);
        annotationField.setAccessible(true);
        annotationField.set(object, propertyValue);
    }
}

