package com.bakhanovich.interviews.trafficLights.configurator;

import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;

/**
 * Configures objects (like BeanPostProcessor in Spring)
 */
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
