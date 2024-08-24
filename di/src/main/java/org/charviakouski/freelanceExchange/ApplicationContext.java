package org.charviakouski.freelanceExchange;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    @Getter
    private Map<Class<?>, Object> beanMap = new HashMap<>();
    private BeanFactory factory;

    @SneakyThrows
    public ApplicationContext(Class<?> application) {
        factory = new BeanFactory(application);
        beanMap = factory.getBeanMap();
    }

    public <T> T getBean(Class<T> type) {
        T t = (T) beanMap.get(type);
        return t;
    }
}






