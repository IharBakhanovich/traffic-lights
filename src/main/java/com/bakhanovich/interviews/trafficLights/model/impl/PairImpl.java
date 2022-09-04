package com.bakhanovich.interviews.trafficLights.model.impl;

import com.bakhanovich.interviews.trafficLights.model.Pair;


public class PairImpl implements Pair {
    private int key;
    private Thread value;

    public PairImpl(int key, Thread value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Thread getValue() {
        return value;
    }
}
