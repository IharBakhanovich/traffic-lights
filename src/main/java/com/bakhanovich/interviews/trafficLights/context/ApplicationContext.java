package com.bakhanovich.interviews.trafficLights.context;

import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.config.Config;
import com.bakhanovich.interviews.trafficLights.factory.ObjectFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages all the application objects. Caches application objects and returns them when it is needed.
 * If there is no an object in the context - asks the ObjectFactory and the ObjectFactory creates it.
 *
 * @author Ihar Bakhanovich.
 */
public class ApplicationContext {
    Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Setter
    private ObjectFactory factory;

    //which packets should be scanned and which implementations exist in the map (in the application).
    @Getter
    private Config config;

    /**
     * Creates the ApplicationContext.
     *
     * @param config is a {@link Config}.
     */
    public ApplicationContext(Config config) {
        this.config = config;
    }

    /**
     * Returns the object by its type.
     *
     * @param type is the type of the object which should be returned.
     * @param <T>  the class of the returned object.
     * @return the object of the {@param type}.
     */
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

        // puts the object in the cache, if it should be created only once.
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }
}
