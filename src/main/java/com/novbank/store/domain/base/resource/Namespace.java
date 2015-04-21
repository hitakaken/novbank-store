package com.novbank.store.domain.base.resource;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by HP on 2015/4/21.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE,ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
@Inherited
@Documented
public @interface Namespace {
    String value() default "";
    String url() default "";
}
