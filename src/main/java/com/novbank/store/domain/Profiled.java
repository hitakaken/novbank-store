package com.novbank.store.domain;

import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/18.
 */
public interface Profiled {
    /**
     * 获取字段列表
     *
     * @return 字段列表
     */
    Set<String> fieldNames();

    /**
     * 获取特定字段所有字段值
     *
     * @param fieldName
     * @return
     */
    Set getFieldValues(String fieldName);
    Set getFieldValues(String fieldName,Map<String,Object> options);
    Set getFieldValuesStrictly(String fieldName,Map<String,Object> options);
    Map<String,Set> getFieldsValues(Iterable<String> fieldNames);
    Map<String,Set> getFieldsValues(Iterable<String> fieldNames,Map<String,Object> options);
    Map<String,Set> getFieldsValuesStrictly(Iterable<String> fieldNames,Map<String,Object> options);

    /**
     * 获取所有字段值（带条件）
     *
     * @param fieldName
     * @return
     */
    Map<Map<String,Object>,Object> getFieldValuesWithOptions(String fieldName);
    Map<Map<String,Object>,Object> getFieldValuesWithOptions(String fieldName,Map<String,Object> options);
    Map<Map<String,Object>,Object> getFieldValuesWithOptionsStrictly(String fieldName,Map<String,Object> options);
    Map<String,Map<Map<String,Object>,Object>> getFieldsValuesWithOptions(Iterable<String> fieldNames);
    Map<String,Map<Map<String,Object>,Object>> getFieldsValuesWithOptions(Iterable<String> fieldNames,Map<String,Object> options);
    Map<String,Map<Map<String,Object>,Object>> getFieldsValuesWithOptionsStrictly(Iterable<String> fieldNames,Map<String,Object> options);

    /**
     * 获取默认字段值
     *
     * @param fieldName 字段名
     * @return
     */
    Object getFieldValue(String fieldName);

    /**
     * 获取最接近条件的字段值
     *
     * @param fieldName 字段名
     * @param options 条件
     * @return
     */
    Object getFieldValue(String fieldName, Map<String,Object> options);
    Object getFieldValueStrictly(String fieldName, Map<String,Object> options);
    //Object getFieldValue(String fieldName, Map<String,Object> options, boolean strict);

    //直接条件查询接口
    Object getFieldValue(String fieldName, String k1, Object v1);
    Object getFieldValue(String fieldName, String k1, Object v1, String k2, Object v2);
    Object getFieldValue(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3);
    Object getFieldValueStrictly(String fieldName, String k1, Object v1);
    Object getFieldValueStrictly(String fieldName, String k1, Object v1, String k2, Object v2);
    Object getFieldValueStrictly(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3);

    /**
     * 设置字段值（无额外属性）
     *
     * @param fieldName
     * @param fieldValue
     */
    void setFieldValue(String fieldName, Object fieldValue);

    /**
     * 设置字段值（额外属性）
     *
     * @param fieldName
     * @param fieldValue
     * @param options
     */
    void setFieldValue(String fieldName, Object fieldValue, Map<String,Object> options);

    void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1);
    void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1, String k2, Object v2);
    void setFieldValue(String fieldName, Object fieldValue, String k1, Object v1, String k2, Object v2, String k3, Object v3);

    /**
     * 设置多个字段（无额外属性）
     *
     * @param values
     */
    void setFieldsValues(Map<String,Object> values);
    void setFieldsValues(Map<String,Object> values,boolean overwrite);

    /**
     * 设置多个字段值（额外属性）
     * @param values
     * @param options
     */
    void setFieldsValues(Map<String,Object> values, Map<String,Object> options);
    void setFieldsValues(Map<String,Object> values, Map<String,Object> options, boolean overwrite);

    void setFieldsValues(Map<String,Object> values, String k1, Object v1);
    void setFieldsValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2);
    void setFieldsValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3);
    void setFieldsValues(Map<String,Object> values, String k1, Object v1, boolean overwrite);
    void setFieldsValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, boolean overwrite);
    void setFieldsValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3, boolean overwrite);

    /**
     * 从另一对象复制
     *
     * @param other
     */
    void setFieldsValues(Profiled other);
    void setFieldsValues(Profiled other, Map<String,Object> options);
    void setFieldsValues(Profiled other, boolean overwrite);
    void setFieldsValues(Profiled other, Map<String,Object> options, boolean overwrite);
}
