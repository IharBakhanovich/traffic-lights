package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.InjectProperty;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.Pair;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.PairImpl;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightImpl;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;
import com.bakhanovich.interviews.trafficLights.service.Initializer;
import lombok.SneakyThrows;

import java.util.*;

/**
 * Initializes the system. The singleton.
 *
 * @author Ihar Bakhanovich.
 */
@Singleton
public class TrafficLightsInitializer implements Initializer {
    public static final String TRAFFIC_LIGHT_INITIALIZATIONS_SUCCESS_MESSAGE
            = "The trafficLight %s was added into the system.";
    //    private static final Logger LOGGER = LogManager.getLogger(TrafficLightsInitializer.class);
    public static final int MILLISECONDS_IN_ONE_SECOND = 1000;
    @InjectProperty
    private String amountOfTrafficLights;
    @InjectByType
    private GeneralStorage repository;// = ObjectFactory.getInstance().createObject(GeneralStorage.class);

    public TrafficLightsInitializer() {
    }

    /**
     * @return {@link GeneralStorage} with all initialized {@link TrafficLight}
     */
    @Override
    public GeneralStorage initTrafficLights() {
        int amountOfTrafficLightsFromProperty = Integer.parseInt(amountOfTrafficLights);
        for (int i = 0; i < amountOfTrafficLightsFromProperty; i++) {
            Random random = new Random();
            TrafficLightImpl trafficLight = new TrafficLightImpl((long) (i + 1),
                    random.nextInt((10 - 2) + 2) + 2);
            repository.add(trafficLight);
            System.out.printf(TRAFFIC_LIGHT_INITIALIZATIONS_SUCCESS_MESSAGE, trafficLight);
        }
        return repository;
    }

    /**
     * Adjust all the {@link TrafficLight} in the system.
     *
     * @param repository is the {@link GeneralStorage} which {@link TrafficLight} are to adjust.
     * @return collection of {@link Pair}: the number of the {@link TrafficLight}
     * and the {@link Thread} where this {@link TrafficLight} works.
     */
    @Override
    public List<Pair> adjustTrafficLightsThreads(GeneralStorage repository) {
        List<Pair> threads = new ArrayList<>();
        for (TrafficLight trafficLight : repository.getData()) {
            Thread thread = new Thread() {
                @Override
                @SneakyThrows
                public void run() {
                    Random random = new Random();

                    Timer timer = new Timer();
                    int timeInterval = trafficLight.getLightsDuration() * MILLISECONDS_IN_ONE_SECOND;

                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            trafficLight.setTrafficLightStatusById(((long) random.nextInt((3 - 1) + 1) + 1));
                        }
                    }, 0, timeInterval);
                }
            };
            Pair pair = new PairImpl(trafficLight.getId().intValue(), thread);
            threads.add(pair);
        }
        return threads;
    }
}
