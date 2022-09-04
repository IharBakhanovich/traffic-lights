package com.bakhanovich.interviews.trafficLights.model.impl;

import com.bakhanovich.interviews.trafficLights.exception.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;

/**
 * Implements {@link TrafficLightStatus}.
 */
public enum TrafficLightStatus {
    OFF(0L),
    RED(1L),
    YELLOW(2L),
    GREEN(3L);

    public static final String TRAFFIC_LIGHT_STATUS_NOT_VALID = "05";

    /**
     * creates the {@link java.util.List <TrafficLightStatus>} with all the values of the {@link TrafficLightStatus}.
     */
    public static final List<TrafficLightStatus> ALL_AVAILABLE_STATUS = Arrays.asList(values());
    private final Long id;

    /**
     * Constructs a new {@link TrafficLightStatus}.
     *
     * @param id is the value of the id of the new {@link TrafficLightStatus}
     */
    TrafficLightStatus(Long id) {
        this.id = id;
    }

    /**
     * Returns the List of all the TrafficLightStatus values.
     *
     * @return {@link List<TrafficLightStatus>}.
     */
    public static List<TrafficLightStatus> valuesAsList() {
        return ALL_AVAILABLE_STATUS;
    }

    /**
     * Returns TrafficLightStatus by id.
     *
     * @param id is the id to search.
     * @return {@link TrafficLightStatus} with the id equals the {@param id}.
     * @throws EntityNotFoundException if such id does not exist.
     */
    public static TrafficLightStatus resolveTrafficLightStatusById(Long id) {
        for (TrafficLightStatus trafficLightStatus : TrafficLightStatus.values()
        ) {
            if (trafficLightStatus.id.equals(id)) {
                return trafficLightStatus;
            }
        }
        throw new EntityNotFoundException(TRAFFIC_LIGHT_STATUS_NOT_VALID,
                String.format("There is no TrafficLightStatus with the id = %s", id));
    }

    /**
     * A Getter for the name.
     *
     * @return The name
     */
    public String getName() {
        return this.name();
    }

    /**
     * A Getter for the id.
     *
     * @return The id.
     */
    public Long getId() {
        return this.id;
    }
}
