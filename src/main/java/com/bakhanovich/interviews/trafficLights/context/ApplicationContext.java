package com.bakhanovich.interviews.trafficLights.context;

import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.config.Config;
import com.bakhanovich.interviews.trafficLights.factory.ObjectFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Setter
    private ObjectFactory factory;
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {

        // ask the map, whether the object already exist
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }
        // if the cache has not this object and if type not an interface - implClass equals type
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            // the config defines the implementation and not the factory
            implClass = config.getImplClass(type);
        }
        final T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }
}
