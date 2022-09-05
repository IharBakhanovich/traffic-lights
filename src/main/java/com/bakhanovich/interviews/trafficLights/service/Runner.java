package com.bakhanovich.interviews.trafficLights.service;

import com.bakhanovich.interviews.trafficLights.model.TrafficLight;

import java.util.List;

/**
 * Manages the work of {@link TrafficLight}
 *
 * @author Ihar Bakhanovich
 */
public interface Runner {
    /**
     * Runs all the {@link TrafficLight}
     */
    void runTrafficLights();

    /**
     * Stops all the {@link TrafficLight}
     */
    void stopTrafficLights();

    /**
     * Returns all the {@link TrafficLight}
     */
    List<TrafficLight> getTrafficLights();
}
