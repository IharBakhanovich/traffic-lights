package com.bakhanovich.interviews.trafficLights.configurator.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.configurator.ObjectConfigurator;
import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Inject in objects its dependencies.
 *
 * @author Ihar Bakhanovich.
 */
public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    /**
     * Configures objects by injecting its dependencies.
     *
     * @param t       is the object, which should be configured.
     * @param context is the {@link ApplicationContext}.
     */
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);

                final Object object = context.getObject(field.getType());//;ObjectFactory.getInstance().createObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
