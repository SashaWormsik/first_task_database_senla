package org.charviakouski.freelanceExchange;

import lombok.Getter;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Component;
import org.charviakouski.freelanceExchange.processorImpl.InjectDependencyInFieldProcessor;
import org.charviakouski.freelanceExchange.processorImpl.InjectDependencyIntoConstructorProcessor;
import org.charviakouski.freelanceExchange.processorImpl.InjectDependencyIntoSetterProcessor;
import org.charviakouski.freelanceExchange.processorImpl.InjectValueInFieldProcessor;
import org.reflections.Reflections;

import java.util.*;

public class ApplicationContext {
    private List<Processor> processors = Arrays.asList(
            new InjectDependencyIntoConstructorProcessor(),
            new InjectDependencyIntoSetterProcessor(),
            new InjectDependencyInFieldProcessor(),
            new InjectValueInFieldProcessor());

    private Set<Class<?>> classes;
    @Getter
    private Map<Class<?>, Object> beanMap = new HashMap<>();
    private Reflections scanner;

    @SneakyThrows
    public ApplicationContext(Class<?> application) {
        scanner = new Reflections(application.getPackage().getName());
        classes = scanner.getTypesAnnotatedWith(Component.class);
        createContext();
    }

    public <T> T getBean(Class<T> type) {
        T t = (T) beanMap.get(type);
        return t;
    }

    public void createBean(Class<?> clazz) {
        processors.forEach(processor -> processor.process(resolveImpl(clazz), this));
    }

    private <T> void createContext() {
        for (Class<?> clazz : classes) {
            createBean(clazz);
        }
    }

    @SneakyThrows
    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            Set<Class<? extends T>> set = scanner.getSubTypesOf(type);
            if (set.size() != 1) {
                throw new IllegalArgumentException(type + " --- has 0 or more than 1 implementation");
            }
            return (Class<T>) set.iterator().next();
        }
        return type;
    }
}






