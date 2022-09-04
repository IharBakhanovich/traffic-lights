package com.bakhanovich.interviews.trafficLights.service;

import com.bakhanovich.interviews.trafficLights.model.Pair;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;

import java.util.List;

public interface Initializer {
    GeneralStorage initTrafficLights();
    List<Pair> adjustTrafficLightsThreads(GeneralStorage repository);
}
