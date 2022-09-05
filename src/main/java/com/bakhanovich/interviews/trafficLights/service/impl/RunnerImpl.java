package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.PostConstruct;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.Pair;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightImpl;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;
import com.bakhanovich.interviews.trafficLights.service.Initializer;
import com.bakhanovich.interviews.trafficLights.service.Runner;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the work of {@link TrafficLight}. The Singleton.
 *
 * @author Ihar Bakhanovich
 */
@Singleton
public class RunnerImpl implements Runner {
    public static final String TRAFFIC_LIGHT_STARTED_MESSAGE = "The traffic light with the id %s has started";
    public static final String TRAFFIC_LIGHT_FINISHED_MESSAGE = "The traffic light with the id %s has finished";
    @InjectByType
    Initializer trafficLightsInitializer;
    private GeneralStorage repository;
    private List<Pair> threads;

    /**
     * Adjust {@link Runner} after it was created.
     */
    @PostConstruct
    public void init() {
        repository = trafficLightsInitializer.initTrafficLights();
        threads = trafficLightsInitializer.adjustTrafficLightsThreads(repository);
    }

    /**
     * Returns all the {@link TrafficLight}
     */
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

    /**
     * Runs all the {@link TrafficLight}
     */
    @Override
    public void runTrafficLights() {
        for (Pair thread : threads) {
            thread.getValue().start();
            System.out.printf(TRAFFIC_LIGHT_STARTED_MESSAGE, thread.getKey());
        }
    }

    /**
     * Stops all the {@link TrafficLight}
     */
    @Override
    public void stopTrafficLights() {
        for (Pair thread : threads) {
            thread.getValue().stop();
            System.out.printf(TRAFFIC_LIGHT_FINISHED_MESSAGE, +thread.getKey());
        }
    }
}
