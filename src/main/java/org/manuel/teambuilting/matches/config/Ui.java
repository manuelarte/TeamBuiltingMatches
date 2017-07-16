package org.manuel.teambuilting.matches.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Manuel Doncel Martos
 * @since 09/07/2017.
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ui {

    Widget widget();

    boolean tableProperty() default false;
}
