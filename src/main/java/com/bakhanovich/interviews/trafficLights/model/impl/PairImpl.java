package com.bakhanovich.interviews.trafficLights.model.impl;

import com.bakhanovich.interviews.trafficLights.model.Pair;

/**
 * Implements the Pair of the int key and the {@link Thread} value.
 *
 * @author Ihar Bakhanovich.
 */
public class PairImpl implements Pair {
    private int key;
    private Thread value;

    /**
     * Constructs a {@link Pair}.
     *
     * @param key   is the int.
     * @param value is the {@link Thread}.
     */
    public PairImpl(int key, Thread value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns a key.
     *
     * @return an int, which is the key of the pair.
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * Returns a value.
     *
     * @return a {@link Thread}, which is the value of the pair.
     */
    @Override
    public Thread getValue() {
        return value;
    }
}
