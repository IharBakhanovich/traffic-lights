package com.bakhanovich.interviews.trafficLights.service;

import com.bakhanovich.interviews.trafficLights.model.TrafficLight;

import java.util.List;

public interface Runner {
    void runTrafficLights();

    void stopTrafficLights();

    List<TrafficLight> getTrafficLights();
}
