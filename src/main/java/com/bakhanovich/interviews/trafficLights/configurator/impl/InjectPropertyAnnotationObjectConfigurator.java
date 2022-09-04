package com.bakhanovich.interviews.trafficLights.configurator.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectProperty;
import com.bakhanovich.interviews.trafficLights.configurator.ObjectConfigurator;
import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Injects object properties, which have annotation @InjectProperty
 */
public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {
    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
// here we can adjust the object. In our case the properties will be injected
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            if (annotation != null) {
                String value = annotation.value().isEmpty()
                        ? propertiesMap.get(field.getName())
                        : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }

        }
    }
}

