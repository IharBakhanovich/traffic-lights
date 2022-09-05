package com.bakhanovich.interviews.trafficLights.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Implements the custom annotation to marc all the properties, which values are to inject at the runtime.
 * If value is "", the name of the application property is equal to the name of the object property.
 * Otherwise the name of the application property can be set as @InjectProperty("property_name").
 * Attention: in the application.properties should be no blank lines, empty spaces and comments.
 *            The format should be: property_name=value
 *
 * @author Ihar Bakhanovich.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectProperty {
    String value() default "";
}
