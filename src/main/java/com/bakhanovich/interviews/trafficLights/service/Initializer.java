package com.bakhanovich.interviews.trafficLights.service;

import com.bakhanovich.interviews.trafficLights.model.Pair;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;

import java.util.List;

/**
 * Initializes the system
 *
 * @author Ihar Bakhanovich
 */
public interface Initializer {

    /**
     * @return {@link GeneralStorage} with all initialized {@link TrafficLight}
     */
    GeneralStorage initTrafficLights();

    /**
     * Adjust all the {@link TrafficLight} in the system.
     *
     * @param repository is the {@link GeneralStorage} which {@link TrafficLight} are to adjust.
     * @return collection of {@link Pair}: the number of the {@link TrafficLight}
     * and the {@link Thread} where this {@link TrafficLight} works.
     */
    List<Pair> adjustTrafficLightsThreads(GeneralStorage repository);
}
