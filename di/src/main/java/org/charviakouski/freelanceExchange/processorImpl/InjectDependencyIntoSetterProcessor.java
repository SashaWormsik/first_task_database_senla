package org.charviakouski.freelanceExchange.processorImpl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.AbstractProcessor;
import org.charviakouski.freelanceExchange.BeanFactory;
import org.charviakouski.freelanceExchange.annotation.Autowired;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class InjectDependencyIntoSetterProcessor extends AbstractProcessor {
    @SneakyThrows
    @Override
    public void process(Class<?> type, BeanFactory factory) {
        if (factory.getBeanMap().containsKey(type)) {
            return;
        }
        Set<Method> annotationMethods = Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.getAnnotation(Autowired.class) != null)
                .collect(Collectors.toSet());
        if (annotationMethods.isEmpty()) {
            return;
        }
        Object bean = type.getConstructor().newInstance();
        for (Method method : annotationMethods) {
            Parameter parameter = method.getParameters()[0];
            Class<?> parameterType = parameter.getType();
            if (!factory.getBeanMap().containsKey(parameterType)) {
                factory.createBean(parameterType);  // TODO
            }
            Object object = factory.getBean(parameterType);
            method.invoke(bean, object);
        }
        putInContext(bean, factory);
    }
}
