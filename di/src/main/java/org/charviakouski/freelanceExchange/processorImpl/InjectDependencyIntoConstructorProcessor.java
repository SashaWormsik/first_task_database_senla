package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.ApplicationContext;
import org.charviakouski.freelanceExchange.annotation.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class InjectDependencyIntoConstructorProcessor extends AbstractProcessor {
    @Override
    @SneakyThrows
    public void process(Class<?> type, ApplicationContext context) {
        if (context.getBeanMap().containsKey(type)) {
            return;
        }
        Constructor<?> constructor = Arrays.stream(type.getDeclaredConstructors())
                .filter(anConstructor -> anConstructor.isAnnotationPresent(Autowired.class))
                .findAny()
                .orElse(null);
        if (constructor == null) {
            return;
        }
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> parameterType = parameters[i].getType();
            if (!context.getBeanMap().containsKey(parameterType)) { // todo
                context.createBean(parameterType);
            }
            Object object = context.getBean(parameterType);
            args[i] = object;
        }
        Object bean = constructor.newInstance(args);
        putInContext(bean, context);
    }
}

