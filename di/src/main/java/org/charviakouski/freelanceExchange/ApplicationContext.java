package org.charviakouski.freelanceExchange;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.annotation.Autowired;
import org.charviakouski.freelanceExchange.annotation.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    public Map<Class<?>, Object> objectMap = new HashMap<>();

    public ApplicationContext(String packagePath) {
        initializeContext(packagePath);
    }

    @lombok.SneakyThrows
    public <T> T getInstance(Class<T> clazz) {
        T object = (T) objectMap.get(clazz);
        Field[] declaredFields = clazz.getDeclaredFields();
        injectAnnotatedField(object, declaredFields);
        return object;
    }

    @SneakyThrows
    private <T> void injectAnnotatedField(T object, Field[] declaredFields) {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object innerObject = objectMap.get(fieldType);
                field.set(object, innerObject);
                injectAnnotatedField(innerObject, fieldType.getDeclaredFields());
            }
        }
    }

    @SneakyThrows
    private void initializeContext(String packagePath) {
        Set<Class<?>> classes = findClasses(packagePath);
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Constructor<?> constructor = clazz.getConstructor();
                Object newInstance = constructor.newInstance();
                objectMap.put(clazz, newInstance);
            }
        }
    }

    private Set<Class<?>> findClasses(String packagePath) {
        InputStream stream = ClassLoader.getSystemClassLoader().
                getResourceAsStream(packagePath.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class<?>> classes = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packagePath))
                .collect(Collectors.toSet());
        return classes;
    }

    @SneakyThrows
    private Class<?> getClass(String className, String packageName) {
        return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));

    }
}
