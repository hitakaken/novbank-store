package com.novbank.store.service.metadata.schema;

import org.springframework.validation.Validator;

import java.util.Map;
import java.util.Set;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface MetaProperty {
    String getName();
    String getNamespace();
    String getAlias();
    PropertyType getType();
    String getLinkedType();
    String getReturnType();
    boolean isReadOnly();
    boolean isProfileOnly();
    boolean isNullable();
    boolean isCached();
    //动态计算
    boolean isQuery();
    String getQueryStrategy();
    boolean isMandatory();
    //校验策略
    String getValidatorStrategy();
    Validator getValidator();
    //缓存策略
    Map<String,Map<String,String>> caches();
    Map<String,Map<String,String>> cachePuts();
    Map<String,Set<String>> cacheEvict();
}
