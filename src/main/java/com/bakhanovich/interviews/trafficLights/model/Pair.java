package com.bakhanovich.interviews.trafficLights.model;

/**
 * Implements the Pair of the int key and the {@link Thread} value.
 *
 * @author Ihar Bakhanovich.
 */
public interface Pair {

    /**
     * Returns a key.
     *
     * @return an int, which is the key of the pair.
     */
    int getKey();

    /**
     * Returns a value.
     *
     * @return a {@link Thread}, which is the value of the pair.
     */
    Thread getValue();
}
