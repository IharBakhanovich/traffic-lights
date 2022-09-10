package com.bakhanovich.interviews.trafficLights.config.impl;

import com.bakhanovich.interviews.trafficLights.config.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * Defines the config of the application.
 *
 * @author Ihar Bakhanovich.
 */
public class JavaConfig implements Config {
    public static final String EXCEPTION_MESSAGE = " has 0 or more than one impl please update your config";
    // has more functionality as in the java reflection class.
    @Getter
    private Reflections scanner;

    // to have an opportunity to choose an implementation from more than one implementation
    private Map<Class, Class> ifc2ImplClass;

    /**
     * Constructs a {@link JavaConfig}.
     *
     * @param packageToScan is the packages to scan.
     * @param ifc2ImplClass is the map of interface against its impl class.
     *                      In the case of the multi-implementation it can be useful.
     */
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
                throw new RuntimeException(ifc + EXCEPTION_MESSAGE);
            }
            return classes.iterator().next();
        });
    }
}
