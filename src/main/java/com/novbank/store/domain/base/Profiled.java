package com.novbank.store.domain.base;

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
    Set values(String fieldName);
    Set values(String fieldName, Map<String, Object> options);
    Set valuesStrictly(String fieldName,Map<String,Object> options);
    Map<String,Set> values(Iterable<String> fieldNames);
    Map<String,Set> values(Iterable<String> fieldNames,Map<String,Object> options);
    Map<String,Set> valuesStrictly(Iterable<String> fieldNames,Map<String,Object> options);

    /**
     * 获取所有字段值（带条件）
     *
     * @param fieldName
     * @return
     */
    Map<Map<String,Object>,Object> valuesWithOptions(String fieldName);
    Map<Map<String,Object>,Object> valuesWithOptions(String fieldName,Map<String,Object> options);
    Map<Map<String,Object>,Object> valuesWithOptionsStrictly(String fieldName,Map<String,Object> options);
    Map<String,Map<Map<String,Object>,Object>> valuesWithOptions(Iterable<String> fieldNames);
    Map<String,Map<Map<String,Object>,Object>> valuesWithOptions(Iterable<String> fieldNames,Map<String,Object> options);
    Map<String,Map<Map<String,Object>,Object>> valuesWithOptionsStrictly(Iterable<String> fieldNames,Map<String,Object> options);

    /**
     * 获取默认字段值
     *
     * @param fieldName 字段名
     * @return
     */
    Object value(String fieldName);

    /**
     * 获取最接近条件的字段值
     *
     * @param fieldName 字段名
     * @param options 条件
     * @return
     */
    Object value(String fieldName, Map<String,Object> options);
    Object valueStrictly(String fieldName, Map<String,Object> options);
    //Object value(String fieldName, Map<String,Object> options, boolean strict);

    //直接条件查询接口
    Object value(String fieldName, String k1, Object v1);
    Object value(String fieldName, String k1, Object v1, String k2, Object v2);
    Object value(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3);
    Object valueStrictly(String fieldName, String k1, Object v1);
    Object valueStrictly(String fieldName, String k1, Object v1, String k2, Object v2);
    Object valueStrictly(String fieldName, String k1, Object v1, String k2, Object v2, String k3, Object v3);

    /**
     * 设置字段值（无额外属性）
     *
     * @param fieldName
     * @param value
     */
    void putValue(String fieldName, Object value);

    /**
     * 设置字段值（额外属性）
     *
     * @param fieldName
     * @param value
     * @param options
     */
    void putValue(String fieldName, Object value, Map<String,Object> options);
    void putValue(String fieldName, Object value, Map<String,Object> options, boolean overwrite);

    void putValue(String fieldName, Object value, String k1, Object v1);
    void putValue(String fieldName, Object value, String k1, Object v1, String k2, Object v2);
    void putValue(String fieldName, Object value, String k1, Object v1, String k2, Object v2, String k3, Object v3);

    /**
     * 设置多个字段（无额外属性）
     *
     * @param values
     */
    void putValues(Map<String,Object> values);
    void putValues(Map<String,Object> values,boolean overwrite);

    /**
     * 设置多个字段值（额外属性）
     * @param values
     * @param options
     */
    void putValues(Map<String,Object> values, Map<String,Object> options);
    void putValues(Map<String,Object> values, Map<String,Object> options, boolean overwrite);

    void putValues(Map<String,Object> values, String k1, Object v1);
    void putValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2);
    void putValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3);
    void putValues(Map<String,Object> values, String k1, Object v1, boolean overwrite);
    void putValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, boolean overwrite);
    void putValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3, boolean overwrite);

    /**
     * 从另一对象复制
     *
     * @param other
     */
    void putValues(Profiled other);
    void putValues(Profiled other, Map<String,Object> options);
    void putValues(Profiled other, boolean overwrite);
    void putValues(Profiled other, Map<String,Object> options, boolean overwrite);
}
