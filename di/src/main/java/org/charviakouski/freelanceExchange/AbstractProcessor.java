package org.charviakouski.freelanceExchange;

import java.util.Arrays;

public abstract class AbstractProcessor implements Processor {

    protected void putInContext(Object bean, BeanFactory factory) {
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length == 0) {
            factory.getBeanMap().put(bean.getClass(), bean);
        } else {
            Arrays.stream(interfaces)
                    .forEach(anInterface -> factory.getBeanMap().put(anInterface, bean));
        }

    }

    protected boolean beanIsPresent(Class<?> type, BeanFactory factory) {
        return factory.getBeanMap().containsKey(type);
    }
}
