package com.novbank.store.service.metadata.schema;

import org.springframework.validation.Validator;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface GlobalProperty {
    String getName();
    String getNameSpace();
    PropertyType getType();
    String validatorString();
    Validator validator();
}
