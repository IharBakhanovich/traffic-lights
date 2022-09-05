package com.bakhanovich.interviews.trafficLights.factory;

import com.bakhanovich.interviews.trafficLights.annotation.PostConstruct;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.configurator.ObjectConfigurator;
import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a Factory for objects. The singleton.
 *
 * @author Ihar Bakhanovich.
 */
@Singleton
public class ObjectFactory {
    @Setter
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    /**
     * Creates a factory.
     *
     * @param context is the {@link ApplicationContext}.
     */
    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        // finds and initialises all the 'ObjectConfigurators'
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    /**
     * Returns object by its interface.
     *
     * @param implClass is the interface, the object from which should be created.
     * @param <T>       is the class of the returned object.
     * @return the created object.
     */
    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        //creating of the object (the constructor is called). At this moment nothing was injected into the object.
        T t = create(implClass);

        // chain of responsibility pattern. Now to add a new configurator this code should be not changed
        configure(t);

        //the responsibility of the fabric to create an object. Object creation is to invoke its constructor.
        // init() is the second constructor. That is why init() is invoked by the fabric.
        // This can not be one of the configurators, because in this case ordering matters. But we do not want that.
        invokeInit(implClass, t);

        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
