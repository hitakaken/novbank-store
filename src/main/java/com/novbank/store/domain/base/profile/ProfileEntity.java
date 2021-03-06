package com.novbank.store.domain.base.profile;

import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * Created by CaoKe on 2015/4/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Persistent
public @interface ProfileEntity {

}
