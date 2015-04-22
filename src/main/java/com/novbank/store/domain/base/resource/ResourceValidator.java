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
public @interface ResourceValidator {
    String value();
}
