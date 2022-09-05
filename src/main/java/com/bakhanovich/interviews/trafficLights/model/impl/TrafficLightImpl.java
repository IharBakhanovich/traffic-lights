package com.bakhanovich.interviews.trafficLights.model.impl;

import com.bakhanovich.interviews.trafficLights.model.TrafficLight;

import java.util.Objects;

/**
 * Implements {@link TrafficLight}.
 *
 * @author Ihar Bakhanovich.
 */
public class TrafficLightImpl implements TrafficLight {
    private Long id;

    private TrafficLightStatus trafficLightStatus;

    private final int trafficLightDuration;
    /**
     * Constructs a new {@link TrafficLight}, the repository entity.
     *
     * @param id is the value of the id of the new {@link TrafficLight}
     * @param trafficLightDuration is the duration of one of the light.
     */
    public TrafficLightImpl(Long id, int trafficLightDuration) {
        this.id = id;
        this.trafficLightStatus = TrafficLightStatus.OFF;
        this.trafficLightDuration = trafficLightDuration;
    }
    /**
     * A Getter for the entity id.
     *
     * @return The entity id.
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * A setter for the entity id.
     *
     * @param id is the id to set.
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * A Getter for the lightDuration of this {@link TrafficLight}.
     *
     * @return the lightDuration.
     */
    @Override
    public int getLightsDuration() {
        return trafficLightDuration;
    }

    /**
     * A Getter for the {@link TrafficLightStatus} of this trafficLight.
     *
     * @return the {@link TrafficLightStatus} of this trafficLight.
     */
    @Override
    public TrafficLightStatus getTrafficLightStatus() {
        return trafficLightStatus;
    }

    /**
     * Sets {@link TrafficLightStatus} by Id.
     *
     * @param statusId is the id of the {@link TrafficLightStatus}
     */
    @Override
    public void setTrafficLightStatusById(Long statusId) {
        this.trafficLightStatus = TrafficLightStatus.resolveTrafficLightStatusById(statusId);
    }

    /**
     * The Setter for the {@link TrafficLightStatus}.
     *
     * @param trafficLightStatus is the {@link TrafficLightStatus} to set.
     */
    public void setTrafficLightStatus(TrafficLightStatus trafficLightStatus) {
        this.trafficLightStatus = trafficLightStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficLightImpl that = (TrafficLightImpl) o;
        return trafficLightDuration == that.trafficLightDuration && Objects.equals(id, that.id) && trafficLightStatus == that.trafficLightStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trafficLightStatus, trafficLightDuration);
    }

    @Override
    public String toString() {
        return "TrafficLightImpl{" +
                "id=" + id +
                ", trafficLightStatus=" + trafficLightStatus +
                ", trafficLightDuration=" + trafficLightDuration +
                '}';
    }

}
