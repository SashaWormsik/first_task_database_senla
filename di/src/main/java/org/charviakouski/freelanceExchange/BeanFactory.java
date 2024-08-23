package org.charviakouski.freelanceExchange;

import lombok.Getter;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.processorImpl.*;
import org.reflections.Reflections;

import java.util.*;

public class BeanFactory {
    private List<Processor> processors = Arrays.asList(
            new InjectDependencyIntoConstructorProcessor(),
            new CreateBeanByDefaultConstructorProcessor(),
            new InjectDependencyIntoSetterProcessor(),
            new InjectDependencyInFieldProcessor(),
            new InjectValueInFieldProcessor());
    @Getter
    private Map<Class<?>, Object> beanMap = new HashMap<>();
    private Reflections scanner;


    public BeanFactory(Class<?> application) {
        scanner = new Reflections(application.getPackage().getName());
        createBeansMap();
    }

    public <T> void createBeansMap() {
        scanner.getTypesAnnotatedWith(Component.class).stream().forEach(this::createBean);
    }

    public void createBean(Class<?> clazz) {
        processors.forEach(processor -> processor.process(resolveImpl(clazz), this));
    }

    public <T> T getBean(Class<T> type) {
        T t = (T) beanMap.get(type);
        return t;
    }

    @SneakyThrows
    private <T> Class<T> resolveImpl(Class<T> type) {
        if (!type.isInterface()) {
            return type;
        }
        Set<Class<? extends T>> set = scanner.getSubTypesOf(type);
        if (set.size() != 1) {
            throw new IllegalArgumentException(type + " --- has 0 or more than 1 implementation");
        }
        return (Class<T>) set.iterator().next();
    }
}
