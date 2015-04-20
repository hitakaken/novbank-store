package com.novbank.store.domain.base.profile;

import com.google.common.base.Predicate;

import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/19.
 */
public abstract class AbstractDelegateProfile implements ProfileSupport {
    public abstract ProfileSupport delegate();

    @Override
    public Set<String> fieldNames() {
        return delegate().fieldNames();
    }

    @Override
    public Set values(String fieldName) {
        return delegate().values(fieldName);
    }

    @Override
    public Set values(String fieldName, Map<String, Object> options) {
        return delegate().values(fieldName, options);
    }

    @Override
    public Set valuesStrictly(String fieldName, Map<String, Object> options) {
        return delegate().valuesStrictly(fieldName, options);
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames) {
        return delegate().values(fieldNames);
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().values(fieldNames, options);
    }

    @Override
    public Map<String, Set> valuesStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().valuesStrictly(fieldNames, options);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName) {
        return delegate().valuesWithOptions(fieldName);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Map<String, Object> options) {
        return delegate().valuesWithOptions(fieldName, options);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptionsStrictly(String fieldName, Map<String, Object> options) {
        return delegate().valuesWithOptionsStrictly(fieldName, options);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames) {
        return delegate().valuesWithOptions(fieldNames);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().valuesWithOptions(fieldNames, options);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptionsStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        return delegate().valuesWithOptionsStrictly(fieldNames, options) ;
    }

    @Override
    public Object value(String fieldName) {
        return delegate().value(fieldName);
    }

    @Override
    public Object value(String fieldName, Map<String, Object> options) {
        return delegate().value(fieldName, options);
    }

    @Override
    public Object valueStrictly(String fieldName, Map<String, Object> options) {
        return delegate().valueStrictly(fieldName, options);
    }

    @Override
    public Object value(String fieldName, String k1, Object v1) {
        return delegate().value(fieldName, k1, v1);
    }

    @Override
    public Object value(String fieldName, String k1, Object v1, String k2, Object v2) {
        return delegate().value(fieldName, k1, v1, k2, v2);
    }

    @Override
    public Object value(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return delegate().value(fieldName, k1, v1, k2, v2, k3, v3);
    }

    @Override
    public Object valueStrictly(String fieldName, String k1, Object v1) {
        return delegate().valueStrictly(fieldName, k1, v1);
    }

    @Override
    public Object valueStrictly(String fieldName, String k1, Object v1, String k2, Object v2) {
        return delegate().valueStrictly(fieldName, k1, v1, k2, v2);
    }

    @Override
    public Object valueStrictly(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return delegate().valueStrictly(fieldName, k1, v1, k2, v2, k3, v3);
    }

    @Override
    public Set values(String fieldName, Predicate predicate) {
        return delegate().values(fieldName,predicate);
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames, Predicate predicate) {
        return delegate().values(fieldNames,predicate);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Predicate predicate) {
        return delegate().valuesWithOptions(fieldName, predicate);
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames, Predicate predicate) {
        return delegate().valuesWithOptions(fieldNames, predicate);
    }

    @Override
    public void putValue(String fieldName, Object value) {
        delegate().putValue(fieldName, value);
    }

    @Override
    public void putValue(String fieldName, Object value, Map<String, Object> options) {
        delegate().putValue(fieldName, value, options);
    }

    @Override
    public void putValue(String fieldName, Object value, Map<String, Object> options, boolean overwrite) {
        delegate().putValue(fieldName, value, options, overwrite);
    }

    @Override
    public void putValue(String fieldName, Object value, String k1, Object v1) {
        delegate().putValue(fieldName, value, k1, v1);
    }

    @Override
    public void putValue(String fieldName, Object value, String k1, Object v1, String k2, Object v2) {
        delegate().putValue(fieldName, value, k1, v1, k2, v2);
    }

    @Override
    public void putValue(String fieldName, Object value, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        delegate().putValue(fieldName, value, k1, v1, k2, v2, k3, v3);
    }

    @Override
    public void putValues(Map<String, Object> values) {
        delegate().putValues(values);
    }

    @Override
    public void putValues(Map<String, Object> values, boolean overwrite) {
        delegate().putValues(values, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, Map<String, Object> options) {
        delegate().putValues(values, options);
    }

    @Override
    public void putValues(Map<String, Object> values, Map<String, Object> options, boolean overwrite) {
        delegate().putValues(values, options, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1) {
        delegate().putValues(values, k1, v1);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2) {
        delegate().putValues(values, k1, v1, k2, v2);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        delegate().putValues(values, k1, v1, k2, v2, k3, v3);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1, boolean overwrite) {
        delegate().putValues(values, k1, v1, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, boolean overwrite) {
        delegate().putValues(values, k1, v1, k2, v2, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3, boolean overwrite) {
        delegate().putValues(values, k1, v1, k2, v2, k3, v3, overwrite);
    }

    @Override
    public void putValues(ProfileSupport other) {
        delegate().putValues(other);
    }

    @Override
    public void putValues(ProfileSupport other, Map<String, Object> options) {
        delegate().putValues(other, options);
    }

    @Override
    public void putValues(ProfileSupport other, boolean overwrite) {
        delegate().putValues(other, overwrite);
    }

    @Override
    public void putValues(ProfileSupport other, Map<String, Object> options, boolean overwrite) {
        delegate().putValues(other, options, overwrite);
    }
}
