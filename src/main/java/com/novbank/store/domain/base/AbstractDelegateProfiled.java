package com.novbank.store.domain.base;

import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/19.
 */
public abstract class AbstractDelegateProfiled implements Profiled{
    public abstract Profiled delegate();

    @Override
    public Set<String> fieldNames() {
        return delegate().fieldNames();
    }

    @Override
    public Set fieldValues(String fieldName) {
        return delegate().fieldValues(fieldName);
    }

    @Override
    public Set fieldValues(String fieldName, Map<String, Object> options) {
        return delegate().fieldValues(fieldName, options);
    }

    @Override
    public Set fieldValuesStrictly(String fieldName, Map<String, Object> options) {
        return delegate().fieldValuesStrictly(fieldName, options);
    }

    @Override
    public Map<String, Set> fieldsValues(Iterable<String> fieldNames) {
        return delegate().fieldsValues(fieldNames);
    }

    @Override
    public Map<String, Set> fieldsValues(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().fieldsValues(fieldNames, options);
    }

    @Override
    public Map<String, Set> fieldsValuesStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().fieldsValuesStrictly(fieldNames, options);
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName) {
        return delegate().fieldValuesWithOptions(fieldName);
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName, Map<String, Object> options) {
        return delegate().fieldValuesWithOptions(fieldName, options);
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptionsStrictly(String fieldName, Map<String, Object> options) {
        return delegate().fieldValuesWithOptionsStrictly(fieldName, options);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptions(Iterable<String> fieldNames) {
        return delegate().fieldsValuesWithOptions(fieldNames);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptions(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().fieldsValuesWithOptions(fieldNames, options);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptionsStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().fieldsValuesWithOptionsStrictly(fieldNames,options) ;
    }

    @Override
    public Object fieldValue(String fieldName) {
        return delegate().fieldValue(fieldName);
    }

    @Override
    public Object fieldValue(String fieldName, Map<String, Object> options) {
        return delegate().fieldValue(fieldName,options);
    }

    @Override
    public Object fieldValueStrictly(String fieldName, Map<String, Object> options) {
        return delegate().fieldValueStrictly(fieldName,options);
    }

    @Override
    public Object fieldValue(String fieldName, String k1, Object v1) {
        return delegate().fieldValue(fieldName,k1,v1);
    }

    @Override
    public Object fieldValue(String fieldName, String k1, Object v1, String k2, Object v2) {
        return delegate().fieldValue(fieldName,k1,v1,k2,v2);
    }

    @Override
    public Object fieldValue(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return delegate().fieldValue(fieldName,k1,v1,k2,v2,k3,v3);
    }

    @Override
    public Object fieldValueStrictly(String fieldName, String k1, Object v1) {
        return delegate().fieldValueStrictly(fieldName,k1,v1);
    }

    @Override
    public Object fieldValueStrictly(String fieldName, String k1, Object v1, String k2, Object v2) {
        return delegate().fieldValueStrictly(fieldName,k1,v1,k2,v2);
    }

    @Override
    public Object fieldValueStrictly(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return delegate().fieldValueStrictly(fieldName,k1,v1,k2,v2,k3,v3);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue) {
        delegate().setFieldValue(fieldName,fieldValue);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options) {
        delegate().setFieldValue(fieldName,fieldValue,options);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options, boolean overwrite) {
        delegate().setFieldValue(fieldName,fieldValue,options,overwrite);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1) {
        delegate().setFieldValue(fieldName,fieldValue,k1,v1);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1, String k2, Object v2) {
        delegate().setFieldValue(fieldName,fieldValue,k1,v1,k2,v2);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        delegate().setFieldValue(fieldName,fieldValue,k1,v1,k2,v2,k3,v3);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values) {
        delegate().setFieldsValues(values);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, boolean overwrite) {
        delegate().setFieldsValues(values,overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, Map<String, Object> options) {
        delegate().setFieldsValues(values,options);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, Map<String, Object> options, boolean overwrite) {
        delegate().setFieldsValues(values,options,overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1) {
        delegate().setFieldsValues(values,k1,v1);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2) {
        delegate().setFieldsValues(values,k1,v1,k2,v2);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        delegate().setFieldsValues(values, k1, v1, k2, v2, k3, v3);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1, boolean overwrite) {
        delegate().setFieldsValues(values,k1,v1,overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, boolean overwrite) {
        delegate().setFieldsValues(values,k1,v1,k2,v2,overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3, boolean overwrite) {
        delegate().setFieldsValues(values,k1,v1,k2,v2,k3,v3,overwrite);
    }

    @Override
    public void setFieldsValues(Profiled other) {
        delegate().setFieldsValues(other);
    }

    @Override
    public void setFieldsValues(Profiled other, Map<String, Object> options) {
        delegate().setFieldsValues(other,options);
    }

    @Override
    public void setFieldsValues(Profiled other, boolean overwrite) {
        delegate().setFieldsValues(other,overwrite);
    }

    @Override
    public void setFieldsValues(Profiled other, Map<String, Object> options, boolean overwrite) {
        delegate().setFieldsValues(other,options,overwrite);
    }
}
