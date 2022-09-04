package com.bakhanovich.interviews.trafficLights.service.impl;

import com.bakhanovich.interviews.trafficLights.annotation.InjectByType;
import com.bakhanovich.interviews.trafficLights.annotation.Singleton;
import com.bakhanovich.interviews.trafficLights.model.TrafficLight;
import com.bakhanovich.interviews.trafficLights.model.impl.TrafficLightStatus;
import com.bakhanovich.interviews.trafficLights.service.Monitor;
import com.bakhanovich.interviews.trafficLights.service.Runner;

@Singleton
public class SystemMonitorImpl implements Monitor {
    @InjectByType
    private Runner runner;

    public SystemMonitorImpl() {
    }

    @Override
    public void monitorSystem() {

        runner.runTrafficLights();

        long beginTime = System.currentTimeMillis();
        long beginTimeForMonitor = beginTime;

//        monitorTrafficLights();
        while ((System.currentTimeMillis() - beginTime) < 30000) {

//            System.out.println("begin time is " + beginTime);
//            System.out.println("current time is " + System.currentTimeMillis());
//            long delta = System.currentTimeMillis() - beginTime;
//            System.out.println("delta is " + delta);
            if ((System.currentTimeMillis() - beginTimeForMonitor) > 3000) {
//                System.out.println("delta2 is " + (System.currentTimeMillis()-beginTimeForMonitor));
                monitorTrafficLights();
                beginTimeForMonitor = System.currentTimeMillis();
            }
//            int timeInterval = 3000; //timer executes every 10 seconds.
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    monitorTrafficLights();
//                }
//            }, 1, timeInterval);
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
        System.out.println("the time is " + System.currentTimeMillis() + "and there are: \n "
                + greenLights + " green traffic lights \n"
                + yellowLights + " yellow traffic lights \n"
                + redLights + " red traffic lights");
    }
}
