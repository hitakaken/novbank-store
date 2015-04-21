package com.novbank.store.service.metadata.support.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.MetaProperty;
import com.novbank.store.service.metadata.schema.PropertyType;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Entity
@XmlRootElement
@JsonRootName("property")
public class ResourceMetaProperty implements MetaProperty{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getNameSpace() {
        return null;
    }

    @Override
    public PropertyType getType() {
        return null;
    }

    @Override
    public MetaClass getLinkedClass() {
        return null;
    }

    @Override
    public PropertyType getLinkedType() {
        return null;
    }

    @Override
    public boolean isJustProfile() {
        return false;
    }

    @Override
    public boolean isReadonly() {
        return false;
    }

    @Override
    public boolean isNotNull() {
        return false;
    }

    @Override
    public String validatorString() {
        return null;
    }

    @Override
    public Validator validator() {
        return null;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }

    @Override
    public String dynamicScript() {
        return null;
    }

    @Override
    public String dynamicScriptType() {
        return null;
    }

    @Override
    public boolean isMandatory() {
        return false;
    }

    @Override
    public boolean isCacheable() {
        return false;
    }

    @Override
    public Map<String, Map<String, String>> caches() {
        return null;
    }

    @Override
    public Map<String, Map<String, String>> cachePuts() {
        return null;
    }

    @Override
    public Map<String, Set<String>> cacheEvict() {
        return null;
    }
}
