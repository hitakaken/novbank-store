package com.novbank.store.domain.base.resource;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by hp on 2015/4/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Inherited
@Persistent
public @interface ResourceQuery {
    String value();
    String name() default "";
    String namespace() default "";
    boolean profileOnly() default true;
    boolean nullable() default true;
    boolean cached() default true;
    String target() default "";
}
