package com.novbank.store.service.metadata.support.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.reflect.TypeToken;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.MetaProperty;
import com.novbank.store.service.metadata.schema.PropertyType;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Entity
@XmlRootElement
@JsonRootName("property")
public class ResourceMetaProperty implements MetaProperty{
    @XmlTransient @JsonIgnore @Transient
    private transient Field field;
    @XmlTransient @JsonIgnore @Transient
    private transient Method getter;
    @XmlTransient @JsonIgnore @Transient
    private transient Method setter;

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String namespace;
    @XmlAttribute
    private String alias;

    @XmlAttribute
    private boolean readOnly = false;
    @XmlAttribute
    private boolean profileOnly = true;
    @XmlAttribute
    private boolean nullable = true;
    @XmlAttribute
    private boolean cached = false;

    @XmlElement(name="validator")
    private String validatorStrategy;

    @XmlTransient @JsonIgnore @Transient
    private transient boolean isQuery = false;

    @XmlElement(name="query")
    private String queryStrategy;

    @XmlTransient @JsonIgnore @Transient
    private transient Validator validator = null;

    public ResourceMetaProperty() {
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public boolean isProfileOnly() {
        return profileOnly;
    }

    public void setProfileOnly(boolean profileOnly) {
        this.profileOnly = profileOnly;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public boolean isCached() {
        return cached;
    }

    public void setCached(boolean cached) {
        this.cached = cached;
    }

    @XmlElement
    private PropertyType type;
    @XmlElement
    private String returnType;
    @XmlElement
    private String linkedType;
    @XmlTransient @JsonIgnore @Transient
    private transient TypeToken rawReturnType;


    @Override
    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String getLinkedType() {
        return linkedType;
    }

    public void setLinkedType(String linkedType) {
        this.linkedType = linkedType;
    }

    public TypeToken getRawReturnType() {
        return rawReturnType;
    }

    public void setRawReturnType(TypeToken rawReturnType) {
        this.rawReturnType = rawReturnType;
    }

    public String getValidatorStrategy() {
        return validatorStrategy;
    }

    public void setValidatorStrategy(String validatorStrategy) {
        this.validatorStrategy = validatorStrategy;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean isQuery() {
        return isQuery;
    }

    @Override
    public String getQueryStrategy() {
        return queryStrategy;
    }

    public void setQueryStrategy(String queryStrategy) {
        this.queryStrategy = queryStrategy;
        this.isQuery = StringUtils.isNotBlank(queryStrategy);
    }

    @Override
    public boolean isMandatory() {
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
