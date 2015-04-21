package com.novbank.store.service.metadata.support.entity;

import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.PropertyType;
import org.springframework.validation.Validator;

/**
 * Created by CaoKe on 2015/4/20.
 */
public class ResourceGlobalProperty implements GlobalProperty{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getNameSpace() {
        return null;
    }

    @Override
    public PropertyType getType() {
        return null;
    }

    @Override
    public String validatorString() {
        return null;
    }

    @Override
    public Validator validator() {
        return null;
    }
}
