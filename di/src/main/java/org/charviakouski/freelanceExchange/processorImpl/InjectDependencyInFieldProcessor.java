package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.ApplicationContext;
import org.charviakouski.freelanceExchange.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InjectDependencyInFieldProcessor extends AbstractProcessor {
    @SneakyThrows
    @Override
    public void process(Class<?> type, ApplicationContext context) {
        if (context.getBeanMap().containsKey(type)) {
            return;
        }
        Set<Field> annotationFields = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .collect(Collectors.toSet());
        if (annotationFields.isEmpty()) {
            return;
        }
        Object object = type.getConstructor().newInstance();
        for (Field field : annotationFields) {
            Class<?> fieldType = field.getType();
            if (!context.getBeanMap().containsKey(fieldType)) { // todo
                context.createBean(fieldType);
            }
            Object injectObject = context.getBean(fieldType);
            field.setAccessible(true);
            field.set(object, injectObject);
        }
        putInContext(object, context);
    }
}
