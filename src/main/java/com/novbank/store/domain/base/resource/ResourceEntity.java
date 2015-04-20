package com.novbank.store.domain.base.resource;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by CaoKe on 2015/4/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Persistent
public @interface ResourceEntity {
    String name() default "";
    String namespace() default "";
    boolean cacheable() default true;
    String[] cacheNames() default {};
}
