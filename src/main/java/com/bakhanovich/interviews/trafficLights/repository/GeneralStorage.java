package com.bakhanovich.interviews.trafficLights.repository;

import com.bakhanovich.interviews.trafficLights.model.TrafficLight;

import java.util.Comparator;
import java.util.List;

/**
 * Implements a repository for {@link TrafficLight}. The Singleton.
 *
 * @author Ihar Bakhanovich
 */
public interface GeneralStorage extends Iterable<TrafficLight> {

    /**
     * Adds a {@link TrafficLight} to the repository.
     *
     * @param object is the {@link TrafficLight} to add.
     * @return the added {@link TrafficLight}.
     */
    TrafficLight add(TrafficLight object);

    /**
     * Gets a {@link TrafficLight} from the repository.
     *
     * @param id is the id, which {@link TrafficLight} is to get.
     * @return a {@link TrafficLight} with the {@param id}.
     */
    TrafficLight get(int id);

    /**
     * Removes a {@link TrafficLight} from the repo.
     *
     * @param index is the id, which {@link TrafficLight} is to remove.
     * @return a {@link TrafficLight} with the {@param id} wich was removed.
     */
    TrafficLight remove(int index);

    /**
     * Gets the size of the repo.
     *
     * @return the amout of the {@link TrafficLight} in the repo.
     */
    int size();

    /**
     * Returns true if the repo is empty.
     *
     * @return true if the repo has no elements.
     */
    boolean isEmpty();

    /**
     * Sorts the repo elements.
     *
     * @param comparator is the Comparator, that describes how elements should be sorted.
     */
    void sort(Comparator<? super TrafficLight> comparator);

    /**
     * Gets all the {@link TrafficLight} from the repo.
     * @return {@link List} of all the {@link TrafficLight}.
     */
    List<TrafficLight> getData();
}
