package com.bakhanovich.interviews.trafficLights.repository;

import com.bakhanovich.interviews.trafficLights.model.TrafficLight;

import java.util.Comparator;
import java.util.List;

public interface GeneralStorage extends Iterable<TrafficLight>{
    TrafficLight add(TrafficLight object);

    TrafficLight get(int id);

    TrafficLight remove(int index);

    int size();

    boolean isEmpty();

    void sort(Comparator<? super TrafficLight> comparator);

    List<TrafficLight> getData();
}
