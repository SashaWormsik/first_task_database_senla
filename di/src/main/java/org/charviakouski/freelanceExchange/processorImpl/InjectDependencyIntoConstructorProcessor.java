package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.BeanFactory;
import org.charviakouski.freelanceExchange.annotation.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

public class InjectDependencyIntoConstructorProcessor extends AbstractProcessor {
    @Override
    @SneakyThrows
    public void process(Class<?> type, BeanFactory factory) {
        if (factory.getBeanMap().containsKey(type)) {
            return;
        }
        Optional<Constructor<?>> optional = Arrays.stream(type.getDeclaredConstructors())
                .filter(anConstructor -> anConstructor.isAnnotationPresent(Autowired.class))
                .findAny();
        if (optional.isPresent()) {
            Constructor<?> constructor = optional.get();
            Parameter[] parameters = optional.get().getParameters();
            Object[] args = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                Class<?> parameterType = parameters[i].getType();
                if (!factory.getBeanMap().containsKey(parameterType)) {
                    factory.createBean(parameterType);
                }
                Object object = factory.getBean(parameterType);
                args[i] = object;
            }
            Object bean = constructor.newInstance(args);
            putInContext(bean, factory);
        }
    }
}

