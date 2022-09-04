package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.PostConstruct;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightImpl;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;
import com.bakhanovich.interviews.trafficLights.service.Initializer;
import com.bakhanovich.interviews.trafficLights.service.Runner;
import com.bakhanovich.interviews.trafficLights.model.Pair;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class RunnerImpl implements Runner {
    @InjectByType
    Initializer trafficLightsInitializer;

    private GeneralStorage repository;
    private List<Pair> threads;

    @PostConstruct
    public void init() {
        repository = trafficLightsInitializer.initTrafficLights();
        threads = trafficLightsInitializer.adjustTrafficLightsThreads(repository);
    }

    @Override
    public List<TrafficLight> getTrafficLights() {
        List<TrafficLight> trafficLightsToReturn = new ArrayList<>();
        for (TrafficLight trafficLight : repository.getData()) {
            TrafficLightImpl trafficLightImpl = new TrafficLightImpl(trafficLight.getId(), trafficLight.getLightsDuration());
            trafficLightImpl.setTrafficLightStatus(trafficLight.getTrafficLightStatus());
            trafficLightsToReturn.add(trafficLightImpl);
        }
        return trafficLightsToReturn;
    }


    @Override
    public void runTrafficLights() {
        for (Pair thread : threads) {
            thread.getValue().start();
            System.out.println("The traffic light with the id " + thread.getKey() + " has started");
        }
    }

    @Override
    public void stopTrafficLights() {
        for (Pair thread : threads) {
            thread.getValue().stop();
            System.out.println("The traffic light with the id " + thread.getKey() + " has finished");
        }
    }
}
