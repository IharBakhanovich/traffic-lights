package com.bakhanovich.interviews.trafficLights.repository.impl;

import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;

import java.io.Serializable;
import java.util.*;

@Singleton
public class RepositoryEntityStorage implements GeneralStorage {
    private final List<TrafficLight> data;

    private int size;
    public RepositoryEntityStorage(Collection<TrafficLight> data) {
        this.data = new ArrayList<>(data);
        this.size = data.size();
    }

    public RepositoryEntityStorage() {
        this.data = new ArrayList<>();
        this.size = 0;
    }

    @Override
    public TrafficLight add(TrafficLight object) {
        if (object.getId() == null) {
            throw new IllegalArgumentException("Entity id must not be null");
        }
        object.setId((long) ++size);
        data.add(object);
        return object;
    }

    @Override
    public TrafficLight get(int id) {
        return data.get(id - 1);
    }

    @Override
    public TrafficLight remove(int index) {
        return data.remove(index - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public void sort(Comparator<? super TrafficLight> comparator) {
        data.sort(comparator);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<TrafficLight> iterator() {
        return data.iterator();
    }

    @Override
    public List<TrafficLight> getData() {
        return data;
    }

}
