package com.bakhanovich.interviews.trafficLights.config;

import org.reflections.Reflections;

/**
 * Defines the config of the system.
 */
public interface Config {
    /**
     * Defines an implementation by an interface class.
     * @param ifc is the interface, which implementation is to define.
     * @param <T> generic
     * @return an implementation of the {@param ifc}
     */
    <T> Class<? extends T> getImplClass(Class<T> ifc);
    Reflections getScanner();
}
