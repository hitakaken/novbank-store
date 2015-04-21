package com.novbank.store.service.metadata.schema;

import org.springframework.validation.Validator;

import java.util.Map;
import java.util.Set;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface MetaProperty {
    String getName();
    String getFullName();
    String getNameSpace();
    PropertyType getType();
    MetaClass getLinkedClass();
    PropertyType getLinkedType();
    boolean isJustProfile();
    boolean isReadonly();
    //校验
    boolean isNotNull();
    String validatorString();
    Validator validator();
    //动态计算
    boolean isDynamic();
    String dynamicScript();
    String dynamicScriptType();
    boolean isMandatory();
    //缓存
    boolean isCacheable();
    Map<String,Map<String,String>> caches();
    Map<String,Map<String,String>> cachePuts();
    Map<String,Set<String>> cacheEvict();
}
