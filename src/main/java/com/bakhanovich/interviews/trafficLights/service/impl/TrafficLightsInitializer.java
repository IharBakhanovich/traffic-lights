package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.InjectProperty;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.PairImpl;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightImpl;
import com.bakhanovich.interviews.trafficLights.repository.GeneralStorage;
import com.bakhanovich.interviews.trafficLights.service.Initializer;
import com.bakhanovich.interviews.trafficLights.model.Pair;
import lombok.SneakyThrows;

import java.util.*;

@Singleton
public class TrafficLightsInitializer implements Initializer {
    @InjectProperty
    private String amountOfTrafficLights;
    //    private static final Logger LOGGER = LogManager.getLogger(TrafficLightsInitializer.class);
    public static final int MILLISECONDS_IN_ONE_SECOND = 1000;

    @InjectByType
    private GeneralStorage repository;// = ObjectFactory.getInstance().createObject(GeneralStorage.class);

    public TrafficLightsInitializer() {
    }

    @Override
    public GeneralStorage initTrafficLights() {
        int amountOfTrafficLightsFromProperty = Integer.parseInt(amountOfTrafficLights);
        for (int i = 0; i < amountOfTrafficLightsFromProperty; i++) {
            Random random = new Random();
            TrafficLightImpl trafficLight = new TrafficLightImpl((long) (i + 1),
                    random.nextInt((10 - 2) + 2) + 2);
            repository.add(trafficLight);
//            LOGGER.info("The trafficLight with the id = " + trafficLight.getId()
//                    + ", with the Status = " + trafficLight.getTrafficLightStatus()
//                    + " and with the lightDuration " + trafficLight.getLightsDuration()
//                    + " was added into the repository. Traffic light is: " + trafficLight);
            System.out.println("The trafficLight " + trafficLight + " was added into the repository. ");
//            LOGGER.info("The trafficLight " + trafficLight + " was added into the repository. ");
        }
//        LOGGER.trace("Initialising ended.");
        return repository;
    }

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
