package com.bakhanovich.interviews.trafficLights;

import com.bakhanovich.interviews.trafficLights.config.impl.JavaConfig;
import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;
import com.bakhanovich.interviews.trafficLights.factory.ObjectFactory;

import java.util.Map;

/**
 * Adjusts the ApplicationContext. Binds ApplicationContext and ObjectFactory.
 *
 * @author Ihar Bakhanovich
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        final JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        final ApplicationContext applicationContext = new ApplicationContext(config);
        final ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setFactory(objectFactory);
        return applicationContext;
    }
}
