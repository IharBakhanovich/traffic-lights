package com.bakhanovich.interviews.trafficLights.configurator;

import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;

/**
 * Configures objects (like BeanPostProcessor in Spring).
 *
 * @author Ihar Bakhanovich.
 */
public interface ObjectConfigurator {

    /**
     * Configures object after its creation.
     *
     * @param t       is the object, which should be configured.
     * @param context is the {@link ApplicationContext}.
     */
    void configure(Object t, ApplicationContext context);
}
