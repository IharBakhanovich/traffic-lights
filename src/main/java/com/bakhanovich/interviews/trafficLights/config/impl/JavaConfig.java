package com.bakhanovich.interviews.trafficLights.config.impl;

import com.bakhanovich.interviews.trafficLights.config.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

// has more functionality as in the java reflection class.
public class JavaConfig implements Config {
    @Getter
    private Reflections scanner;

    // to have an opportunity to choose an implementation from more than one implementation
    private Map<Class, Class> ifc2ImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2ImplClass = ifc2ImplClass;
    }

    /**
     * Defines an implementation by an interface class.
     *
     * @param ifc is the interface, which implementation is to define.
     * @return an implementation of the {@param ifc}
     */
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {

        //if the map has an implementation (has the key-value for an asked interface) - it will be return
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            // in the most cases there will be the one implementation of an interface
            //if the map has no implementation - the lambda will start and the founded implementation will be set in the map
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                throw new RuntimeException(ifc + " has 0 or more than one impl please update your config");
            }
            return classes.iterator().next();
        });
    }
}
