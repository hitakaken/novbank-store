package com.novbank.store.service.metadata.schema;

import org.springframework.validation.Validator;

/**
 * Created by HP on 2015/4/16.
 */
public interface GlobalProperty {
    String getName();
    String getNameSpace();
    PropertyType getType();
    String validatorString();
    Validator validator();
}
