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
public @interface ResourceRelation {
    String value() default "";
    String type();
    String direction();
    String namespace() default "";
    boolean readOnly() default false;
    boolean nullable() default true;
    boolean cached() default false;
    String target() default "";
}
