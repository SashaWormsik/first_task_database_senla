package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.BeanFactory;
import org.charviakouski.freelanceExchange.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InjectDependencyInFieldProcessor extends AbstractProcessor {
    @SneakyThrows
    @Override
    public void process(Class<?> type, BeanFactory factory) {
        if (beanIsPresent(type, factory)) {
            return;
        }
        Set<Field> annotationFields = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .collect(Collectors.toSet());
        if (annotationFields.isEmpty()) {
            return;
        }
        Object object = type.getConstructor().newInstance();
        annotationFields.forEach(field -> createObject(field, object, factory));
        putInContext(object, factory);
    }

    @SneakyThrows
    private void createObject(Field annotationField, Object object, BeanFactory factory) {
        Class<?> fieldType = annotationField.getType();
        if (!factory.getBeanMap().containsKey(fieldType)) {
            factory.createBean(fieldType);
        }
        Object injectObject = factory.getBean(fieldType);
        annotationField.setAccessible(true);
        annotationField.set(object, injectObject);
    }
}
