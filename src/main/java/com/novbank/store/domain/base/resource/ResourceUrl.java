package com.novbank.store.domain.base.resource;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by Cao Ke on 2015/4/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Inherited
@Persistent
public @interface ResourceUrl {

}
