package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightStatus;
import com.bakhanovich.interviews.trafficLights.service.Monitor;
import com.bakhanovich.interviews.trafficLights.service.Runner;

/**
 * Monitors the work of the System. The Singleton.
 *
 * @author Ihar Bakhanovich
 */
@Singleton
public class SystemMonitorImpl implements Monitor {
    public static final String RESULT_CONSOLE_MESSAGE
            = "The time is %s and there are: \n %s green traffic lights \n %s yellow traffic lights \n %s red traffic lights%n";
    @InjectByType
    private Runner runner;

    public SystemMonitorImpl() {
    }

    /**
     * Monitors the work of the system
     */
    @Override
    public void monitorSystem() {

        runner.runTrafficLights();

        long beginTime = System.currentTimeMillis();
        long beginTimeForMonitor = beginTime;

//        monitorTrafficLights();
        while ((System.currentTimeMillis() - beginTime) < 30000) {

            if ((System.currentTimeMillis() - beginTimeForMonitor) > 3000) {
                monitorTrafficLights();
                beginTimeForMonitor = System.currentTimeMillis();
            }
        }

        runner.stopTrafficLights();
        System.exit(0); //todo
    }

    private void monitorTrafficLights() {
        int greenLights = 0;
        int yellowLights = 0;
        int redLights = 0;
        for (TrafficLight trafficLight : runner.getTrafficLights()) {
            if (trafficLight.getTrafficLightStatus() == TrafficLightStatus.YELLOW) {
                yellowLights++;
            }
            if (trafficLight.getTrafficLightStatus() == TrafficLightStatus.GREEN) {
                greenLights++;
            }
            if (trafficLight.getTrafficLightStatus() == TrafficLightStatus.RED) {
                redLights++;
            }

        }
        System.out.printf(RESULT_CONSOLE_MESSAGE, System.currentTimeMillis(), greenLights, yellowLights, redLights);
    }
}
