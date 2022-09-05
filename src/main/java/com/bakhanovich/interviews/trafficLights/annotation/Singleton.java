package com.bakhanovich.interviews.trafficLights.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Implements the custom annotation to marc all the application singletons.
 *
 * @author Ihar Bakhanovich.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
}
