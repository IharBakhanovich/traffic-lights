package com.bakhanovich.interviews.trafficLights;

import com.bakhanovich.interviews.trafficLights.context.ApplicationContext;
import com.bakhanovich.interviews.trafficLights.service.Monitor;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        TrafficLightsInitializer trafficLightsInitializer = new TrafficLightsInitializer();
//        GeneralStorage repository = trafficLightsInitializer.initTrafficLights(10);
//        for (TrafficLight trafficLight: repository.getData()) {
//            System.out.println("the entity in the repository with the id" + trafficLight.getId() + "is " + trafficLight);
//        }
//
//        List<Thread> threads = trafficLightsInitializer.adjustTrafficLightsThreads(repository);
//        for (Thread thread : threads) {
//            System.out.println("The thread " + thread + " was created");
//        }
// ObjectFactory is used here because it is the configuration code,
// which tells about the main service, which is created and used
//        Monitor monitor = ObjectFactory.getInstance().createObject(Monitor.class);
        final ApplicationContext context = Application.run("com.bakhanovich", new HashMap<>());
        final Monitor monitor = context.getObject(Monitor.class);
        monitor.monitorSystem();
    }
}
