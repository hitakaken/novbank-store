package com.novbank.store.domain.document;

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
     * 获取所有字段值
     *
     * @param fieldName 字段名
     * @return
     */
    Set getFieldValues(String fieldName);

    /**
     * 获取所有字段值（带条件）
     *
     * @param fieldName
     * @return
     */
    Map<Map<String,Object>,Object> getFieldValuesWithOptions(String fieldName);

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

    /**
     * 按条件获取字段值
     *
     * @param fieldName 字段名
     * @param options 条件
     * @param strict 是否严格
     * @return
     */
    Object getFieldValue(String fieldName, Map<String,Object> options, boolean strict);

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
    void setFieldValues(Map<String,Object> values);

    /**
     * 设置多个字段值（额外属性）
     * @param values
     * @param options
     */
    void setFieldValues(Map<String,Object> values, Map<String,Object> options);

    void setFieldValues(Map<String,Object> values, String k1, Object v1);
    void setFieldValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2);
    void setFieldValues(Map<String,Object> values, String k1, Object v1, String k2, Object v2, String k3, Object v3);

    /**
     * 从另一对象复制
     *
     * @param other
     */
    void setFieldValues(Profiled other);

}
