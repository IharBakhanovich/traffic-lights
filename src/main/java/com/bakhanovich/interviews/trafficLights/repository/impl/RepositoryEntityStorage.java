package com.bakhanovich.interviews.trafficLights.repository.impl;

import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;

import java.util.*;

/**
 * Implements a repository for {@link TrafficLight}. The Singleton.
 *
 * @author Ihar Bakhanovich
 */
@Singleton
public class RepositoryEntityStorage implements GeneralStorage {
    private final List<TrafficLight> data;

    /**
     * Constructs new repository with the collection of {@link TrafficLight}
     */
    private int size;

    public RepositoryEntityStorage(Collection<TrafficLight> data) {
        this.data = new ArrayList<>(data);
        this.size = data.size();
    }

    /**
     * Constructs empty repo.
     */
    public RepositoryEntityStorage() {
        this.data = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Adds a {@link TrafficLight} to the repository.
     *
     * @param object is the {@link TrafficLight} to add.
     * @return the added {@link TrafficLight}.
     */
    @Override
    public TrafficLight add(TrafficLight object) {
        if (object.getId() == null) {
            throw new IllegalArgumentException("Entity id must not be null");
        }
        object.setId((long) ++size);
        data.add(object);
        return object;
    }

    /**
     * Gets a {@link TrafficLight} from the repository.
     *
     * @param id is the id, which {@link TrafficLight} is to get.
     * @return a {@link TrafficLight} with the {@param id}.
     */
    @Override
    public TrafficLight get(int id) {
        return data.get(id - 1);
    }

    /**
     * Removes a {@link TrafficLight} from the repo.
     *
     * @param index is the id, which {@link TrafficLight} is to remove.
     * @return a {@link TrafficLight} with the {@param id} wich was removed.
     */
    @Override
    public TrafficLight remove(int index) {
        return data.remove(index - 1);
    }

    /**
     * Gets the size of the repo.
     *
     * @return the amout of the {@link TrafficLight} in the repo.
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Returns true if the repo is empty.
     *
     * @return true if the repo has no elements.
     */
    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /**
     * Sorts the repo elements.
     *
     * @param comparator is the Comparator, that describes how elements should be sorted.
     */
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

    /**
     * Gets all the {@link TrafficLight} from the repo.
     *
     * @return {@link List} of all the {@link TrafficLight}.
     */
    @Override
    public List<TrafficLight> getData() {
        return data;
    }

}
