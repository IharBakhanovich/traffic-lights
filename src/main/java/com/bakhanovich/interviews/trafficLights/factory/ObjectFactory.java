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

@Singleton
public class ObjectFactory {
    @Setter
    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        // initialisation of the 'configurators'
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        //creating of the object (the constructor is called). At this moment nothing was injected into the object.
        T t = create(implClass);

        // chain of responsibility pattern. Now to add a new configurator this code should be not changed
        configure(t);

        //the responsibility of the fabric to create an object. Object creation is to invoke its constructor.
        // init() is the second constructor. That is why init() is invoked by the fabric.
        // This can not be one of the configurators, because in this case ordering matters. But we do not want that.
        for (Method method : implClass.getMethods()) {
            if(method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }

        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
