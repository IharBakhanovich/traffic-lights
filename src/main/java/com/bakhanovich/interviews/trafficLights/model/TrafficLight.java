package com.bakhanovich.interviews.trafficLights.model;

import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightStatus;

/**
 * Implements the TrafficLight.
 */
public interface TrafficLight {
    /**
     * A Getter for the entity id.
     *
     * @return The entity id.
     */
    Long getId();

    /**
     * A setter for the entity id.
     *
     * @param id is the id to set.
     */
    void setId(Long id);

    /**
     * A Getter for the lightDuration of this {@link TrafficLight}.
     *
     * @return the lightDuration.
     */
    int getLightsDuration();

    /**
     * A Getter for the {@link TrafficLightStatus} of this trafficLight.
     *
     * @return the {@link TrafficLightStatus} of this trafficLight.
     */
    TrafficLightStatus getTrafficLightStatus();

    /**
     * Sets {@link TrafficLightStatus} by Id.
     *
     * @param statusId is the id of the {@link TrafficLightStatus}
     */
    void setTrafficLightStatusById(Long statusId);
}
