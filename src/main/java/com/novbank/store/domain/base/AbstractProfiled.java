package com.novbank.store.domain.base;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 2015/4/18.
 */
public abstract class AbstractProfiled implements Profiled {
    @Override
    public abstract void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options, boolean overwrite);

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options){
        setFieldValue(fieldName,fieldValue,options,true);
    }

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

    @Override
    public void setFieldsValues(Map<String, Object> values, Map<String, Object> options, boolean overwrite) {
        if(values == null || values.isEmpty()) return;
        for(String fieldName : values.keySet()){
            setFieldValue(fieldName,values.get(fieldName),options,overwrite);
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

    public abstract Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly);

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName, Map<String, Object> options) {
        return fieldValuesWithOptions(fieldName,options,false);
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptionsStrictly(String fieldName, Map<String, Object> options) {
        return fieldValuesWithOptions(fieldName,options,true);
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName) {
        return fieldValuesWithOptions(fieldName,null);
    }

    @Override
    public Set fieldValues(String fieldName) {
        Map<Map<String, Object>, Object> result = fieldValuesWithOptions(fieldName);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Set fieldValues(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result =  fieldValuesWithOptions(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Set fieldValuesStrictly(String fieldName, Map<String, Object> options) {
        Map<Map<String, Object>, Object> result = fieldValuesWithOptionsStrictly(fieldName, options);
        return result!=null && !result.isEmpty() ? Sets.newHashSet(result.values()) :null;
    }

    @Override
    public Map<String, Set> fieldsValues(Iterable<String> fieldNames) {
        Map<String,Set> results = Maps.newHashMap();
        return results;
    }

    @Override
    public Map<String, Set> fieldsValues(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = fieldValues(fieldName,options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Set> fieldsValuesStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String,Set> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Set result = fieldValuesStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptions(Iterable<String> fieldNames) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = fieldValuesWithOptions(fieldName);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptions(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = fieldValuesWithOptions(fieldName,options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    @Override
    public Map<String, Map<Map<String, Object>, Object>> fieldsValuesWithOptionsStrictly(Iterable<String> fieldNames, Map<String, Object> options) {
        Map<String, Map<Map<String, Object>, Object>> results = Maps.newHashMap();
        if(fieldNames!=null){
            for(String fieldName : fieldNames){
                Map<Map<String, Object>, Object> result = fieldValuesWithOptionsStrictly(fieldName, options);
                if(result!=null) results.put(fieldName,result);
            }
        }
        return results;
    }

    public abstract Object fieldValue(String fieldName, Map<String,Object> options, boolean strict);

    @Override
    public Object fieldValue(String fieldName, Map<String, Object> options) {
        return fieldValue(fieldName,options,false);
    }

    @Override
    public Object fieldValueStrictly(String fieldName, Map<String, Object> options) {
        return fieldValue(fieldName,options,true);
    }

    @Override
    public Object fieldValue(String fieldName) {
        return fieldValue(fieldName,null);
    }

    @Override
    public Object fieldValue(String fieldName, final String k1, final Object v1) {
        return fieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);}});
    }

    @Override
    public Object fieldValue(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return fieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);}});
    }

    @Override
    public Object fieldValue(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return fieldValue(fieldName,new HashMap<String, Object>(){{put(k1,v1);put(k2,v2);put(k3,v3);}});
    }

    @Override
    public Object fieldValueStrictly(String fieldName, final String k1, final Object v1) {
        return fieldValueStrictly(fieldName, new HashMap<String, Object>() {{
            put(k1, v1);
        }});
    }

    @Override
    public Object fieldValueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2) {
        return fieldValueStrictly(fieldName, new HashMap<String, Object>() {{put(k1, v1);put(k2, v2);}});
    }

    @Override
    public Object fieldValueStrictly(String fieldName, final String k1, final Object v1, final String k2, final Object v2, final String k3, final Object v3) {
        return fieldValueStrictly(fieldName, new HashMap<String, Object>() {{put(k1, v1);put(k2, v2);put(k3, v3);}});
    }

    @Override
    public void setFieldsValues(Profiled other, Map<String, Object> options, boolean overwrite) {
        if(other == null || other.fieldNames() ==null || other.fieldNames().isEmpty())
            return;
        for(String fieldName : other.fieldNames()){
            for(Map.Entry<Map<String,Object>,Object> entry:other.fieldValuesWithOptions(fieldName).entrySet()){
                Map<String,Object> key = entry.getKey();
                Object value = entry.getValue();
                if(options!=null) key.putAll(options);
                setFieldValue(fieldName,value,options,overwrite);
            }
        }
    }

    @Override
    public void setFieldsValues(Profiled other, Map<String, Object> options) {
        setFieldsValues(other,options,false);
    }

    @Override
    public void setFieldsValues(Profiled other, boolean overwrite) {
        setFieldsValues(other,null,overwrite);
    }

    @Override
    public void setFieldsValues(Profiled other) {
        setFieldsValues(other,false);
    }
}
