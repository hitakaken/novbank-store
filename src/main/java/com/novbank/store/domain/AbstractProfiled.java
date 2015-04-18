package com.novbank.store.domain;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 2015/4/18.
 */
public abstract class AbstractProfiled implements Profiled {
    public abstract void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options);

    @Override
    public void setFieldValue(String fieldName, Object fieldValue) {
        setFieldValue(fieldName,fieldValue,null);
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, final String k1, final Object v1) {
        setFieldValue(fieldName,fieldValue, new HashMap<String, Object>(){{put(k1,v1);}});
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, final String k1, final Object v1, final String k2, final Object v2) {
        setFieldValue(fieldName,fieldValue, new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);}});
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        setFieldValue(fieldName,fieldValue, new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);put(k3,v3);}});
    }

    public abstract boolean containsFieldOptions(String fieldName, Map<String,Object> options);

    @Override
    public void setFieldsValues(Map<String, Object> values, Map<String, Object> options, boolean overwrite) {
        if(values == null || values.isEmpty()) return;
        for(String fieldName : values.keySet()){
            if(!overwrite && containsFieldOptions(fieldName,options))
                continue;
            setFieldValue(fieldName,options);
        }
    }

    @Override
    public void setFieldsValues(Map<String, Object> values) {
        setFieldsValues(values,true);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, boolean overwrite) {
        setFieldsValues(values,null,overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, Map<String, Object> options) {
        setFieldsValues(values,options,true);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);}});
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);}});
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);put(k3,v3);}});
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1, boolean overwrite) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);}},overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, boolean overwrite) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);}},overwrite);
    }

    @Override
    public void setFieldsValues(Map<String, Object> values, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3, boolean overwrite) {
        setFieldsValues(values,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);put(k3,v3);}},overwrite);
    }

    public abstract Map<Map<String, Object>, Object> getFieldValuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly);

    @Override
    public Map<Map<String, Object>, Object> getFieldValuesWithOptions(String fieldName, Map<String, Object> options) {
        return getFieldValuesWithOptions(fieldName,options,false);
    }

    @Override
    public Map<Map<String, Object>, Object> getFieldValuesWithOptionsStrictly(String fieldName, Map<String, Object> options) {
        return getFieldValuesWithOptions(fieldName,options,true);
    }

    @Override
    public Map<Map<String, Object>, Object> getFieldValuesWithOptions(String fieldName) {
        return getFieldValuesWithOptions(fieldName,null);
    }

    @Override
    public Set getFieldValues(String fieldName) {
        Map<Map<String, Object>, Object> result = getFieldValuesWithOptions(fieldName);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Set getFieldValues(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result =  getFieldValuesWithOptions(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Set getFieldValuesStrictly(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result = getFieldValuesWithOptionsStrictly(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Map<String, Set> getFieldsValues(Iterable<String> fieldNames) {
        Map<String,Set> results = Maps.newHashMap();
        return results;
    }

    @Override
    public Map<String, Set> getFieldsValues(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = getFieldValues(fieldName,options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Set> getFieldsValuesStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = getFieldValuesStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> getFieldsValuesWithOptions(Iterable<String> fieldNames) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = getFieldValuesWithOptions(fieldName);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> getFieldsValuesWithOptions(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = getFieldValuesWithOptions(fieldName,options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> getFieldsValuesWithOptionsStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = getFieldValuesWithOptionsStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    public abstract Object getFieldValue(String fieldName, Map<String,Object> options, boolean strict);

    @Override
    public Object getFieldValue(String fieldName, Map<String, Object> options) {
        return getFieldValue(fieldName,options,false);
    }

    @Override
    public Object getFieldValueStrictly(String fieldName, Map<String, Object> options) {
        return getFieldValue(fieldName,options,true);
    }

    @Override
    public Object getFieldValue(String fieldName) {
        return getFieldValue(fieldName,null);
    }

    @Override
    public Object getFieldValue(String fieldName, final String k1, final Object v1) {
        return getFieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);}});
    }

    @Override
    public Object getFieldValue(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return getFieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);}});
    }

    @Override
    public Object getFieldValue(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return getFieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);put(k3,v3);}});
    }

    @Override
    public Object getFieldValueStrictly(String fieldName, final String k1, final Object v1) {
        return getFieldValueStrictly(fieldName, new HashMap<String, Object>() {{put(k1, v1);}});
    }

    @Override
    public Object getFieldValueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return getFieldValueStrictly(fieldName, new HashMap<String, Object>() {{put(k1, v1);put(k2, v2);}});
    }

    @Override
    public Object getFieldValueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return getFieldValueStrictly(fieldName, new HashMap<String, Object>() {{put(k1, v1);put(k2, v2);put(k3, v3);}});
    }
}
