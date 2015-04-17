package com.novbank.store.crossstore;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by HP on 2015/4/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Persistent
@Documented
public @interface Profiled {

}
