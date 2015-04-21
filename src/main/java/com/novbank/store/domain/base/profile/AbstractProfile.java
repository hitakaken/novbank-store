package com.novbank.store.domain.base.profile;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cao Ke on 2015/4/18.
 */
public abstract class AbstractProfile implements ProfileSupport {
    @Override
    public abstract void putValue(String fieldName, Object value, Map<String, Object> options, boolean overwrite);

    @Override
    public void putValue(String fieldName, Object value, Map<String, Object> options){
        putValue(fieldName, value, options, true);
    }

    @Override
    public void putValue(String fieldName, Object value) {
        putValue(fieldName, value, null);
    }

    @Override
    public void putValue(String fieldName, Object value, final String k1, final Object v1) {
        putValue(fieldName, value, new HashMap<String, Object>() {{
            put(k1, v1);
        }});
    }

    @Override
    public void putValue(String fieldName, Object value, final String k1, final Object v1, final String k2, final Object v2) {
        putValue(fieldName, value, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
        }});
    }

    @Override
    public void putValue(String fieldName, Object value, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        putValue(fieldName, value, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }});
    }

    @Override
    public void putValues(Map<String, Object> values, Map<String, Object> options, boolean overwrite) {
        if(values == null || values.isEmpty()) return;
        for(String fieldName : values.keySet()){
            putValue(fieldName, values.get(fieldName), options, overwrite);
        }
    }

    @Override
    public void putValues(Map<String, Object> values) {
        putValues(values, true);
    }

    @Override
    public void putValues(Map<String, Object> values, boolean overwrite) {
        putValues(values, null, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, Map<String, Object> options) {
        putValues(values, options, true);
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
        }});
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
        }});
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }});
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1, boolean overwrite) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
        }}, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, boolean overwrite) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
        }}, overwrite);
    }

    @Override
    public void putValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3, boolean overwrite) {
        putValues(values, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }}, overwrite);
    }

    public abstract Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly);

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Map<String, Object> options) {
        return valuesWithOptions(fieldName, options, false);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptionsStrictly(String fieldName, Map<String, Object> options) {
        return valuesWithOptions(fieldName, options, true);
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName) {
        return valuesWithOptions(fieldName, Maps.<String, Object>newHashMap());
    }

    @Override
    public Set values(String fieldName) {
        Map<Map<String, Object>, Object> result = valuesWithOptions(fieldName);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) : Sets.newHashSet();
    }

    @Override
    public Set values(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result =  valuesWithOptions(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :Sets.newHashSet();
    }

    @Override
    public Set valuesStrictly(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result = valuesWithOptionsStrictly(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :Sets.newHashSet();
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames) {
        return values(fieldNames, Maps.<String, Object>newHashMap());
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = values(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Set> valuesStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = valuesStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames) {
        return valuesWithOptions(fieldNames, Maps.<String, Object>newHashMap());
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = valuesWithOptions(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptionsStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = valuesWithOptionsStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    public abstract Object value(String fieldName, Map<String,Object> options, boolean strict);

    @Override
    public Object value(String fieldName, Map<String, Object> options) {
        return value(fieldName, options, false);
    }

    @Override
    public Object valueStrictly(String fieldName, Map<String, Object> options) {
        return value(fieldName, options, true);
    }

    @Override
    public Object value(String fieldName) {
        return value(fieldName, null);
    }

    @Override
    public Object value(String fieldName, final String k1, final Object v1) {
        return value(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
        }});
    }

    @Override
    public Object value(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return value(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
        }});
    }

    @Override
    public Object value(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return value(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }});
    }

    @Override
    public Object valueStrictly(String fieldName, final String k1, final Object v1) {
        return valueStrictly(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
        }});
    }

    @Override
    public Object valueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return valueStrictly(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
        }});
    }

    @Override
    public Object valueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return valueStrictly(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
            put(k2, v2);
            put(k3, v3);
        }});
    }

    @Override
    public void putValues(ProfileSupport other, Map<String, Object> options, boolean overwrite) {
        if(other == null || other.fieldNames() ==null || other.fieldNames().isEmpty())
            return;
        for(String fieldName : other.fieldNames()){
            for(Map.Entry<Map<String,Object>,Object> entry:other.valuesWithOptions(fieldName).entrySet()){
                Map<String,Object> key = entry.getKey();
                Object value = entry.getValue();
                if(options!=null) key.putAll(options);
                putValue(fieldName, value, options, overwrite);
            }
        }
    }

    @Override
    public void putValues(ProfileSupport other, Map<String, Object> options) {
        putValues(other, options, false);
    }

    @Override
    public void putValues(ProfileSupport other, boolean overwrite) {
        putValues(other, null, overwrite);
    }

    @Override
    public void putValues(ProfileSupport other) {
        putValues(other, false);
    }

    @Override
    public abstract Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Predicate predicate);

    @Override
    public Set values(String fieldName, Predicate predicate) {
        Map<Map<String, Object>, Object> results = valuesWithOptions(fieldName,predicate);
        return results!=null && !results.isEmpty() ? Sets.newHashSet(results.values()) :Sets.newHashSet();
    }

    @Override
    public Map<String, Set> values(Iterable<String> fieldNames, Predicate predicate) {
        Map<String, Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = values(fieldName, predicate);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> valuesWithOptions(Iterable<String> fieldNames, Predicate predicate) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = valuesWithOptions(fieldName, predicate);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }
}
