package com.bakhanovich.interviews.trafficLights.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Implements the custom annotation to marc all the object dependencies, which should be injected at the runtime.
 * IoC annotation.
 *
 * @author Ihar Bakhanovich.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectByType {
}
