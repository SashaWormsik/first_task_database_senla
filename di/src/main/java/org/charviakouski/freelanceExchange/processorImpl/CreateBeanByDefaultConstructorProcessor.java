package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.ApplicationContext;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Value;

import java.util.Arrays;

public class CreateBeanByDefaultConstructorProcessor extends AbstractProcessor {
    @Override
    @SneakyThrows
    public void process(Class<?> type, ApplicationContext context) {
        if (context.getBeanMap().containsKey(type)) {
            return;
        }
        boolean autowiredPresented = Arrays.stream(type.getConstructors())
                .anyMatch(constructor -> constructor.isAnnotationPresent(Autowired.class));
        boolean isAnyFieldAnnotated = Arrays.stream(type.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(Autowired.class) || field.isAnnotationPresent(Value.class));
        boolean isAnySetAnnotated = Arrays.stream(type.getDeclaredMethods())
                .anyMatch(method -> method.isAnnotationPresent(Autowired.class));
        if (isAnyFieldAnnotated || autowiredPresented || isAnySetAnnotated) {
            return;
        }
        putInContext(type.getConstructor().newInstance(), context);
    }
}
