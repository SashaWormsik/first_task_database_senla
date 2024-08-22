package org.charviakouski.freelanceExchange;

public abstract class AbstractProcessor implements Processor {

    protected void putInContext(Object bean, ApplicationContext context) {
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length == 0) {
            context.getBeanMap().put(bean.getClass(), bean);
        } else {
            for (Class<?> anInterface : interfaces) {
                context.getBeanMap().put(anInterface, bean);
            }
        }
    }
}
