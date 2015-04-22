package com.novbank.store.service.metadata.support.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.MetaIndex;
import com.novbank.store.service.metadata.schema.MetaProperty;
import groovy.lang.MetaMethod;
import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.ast.ClassNode;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Entity
@XmlRootElement
@JsonRootName("class")
@XmlAccessorType (XmlAccessType.FIELD)
public class ResourceMetaClass implements MetaClass {
    @XmlAttribute
    private String name;

    @XmlAttribute(required = false)
    private String namespace;

    @XmlAttribute(required = false)
    private boolean isAbstract = false;

    @XmlAttribute(required = false)
    private String superClassName;

    @XmlTransient @JsonIgnore @Transient
    private transient ResourceMetaClass superClass;

    @XmlElement(name = "javaType", required = false, nillable =false)
    private String javaClassName;

    @XmlTransient @JsonIgnore @Transient
    private transient Class javaClass;

    @XmlElement(name = "properties")
    private List<ResourceMetaProperty> declaredProperties;

    @XmlAttribute(required = false)
    private String cacheName;

    @XmlTransient @JsonIgnore @Transient
    private transient boolean initialized = false;

    public ResourceMetaClass() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    @Override
    public ResourceMetaClass getSuperClass() {
        return superClass;
    }

    public void setSuperClass(ResourceMetaClass superClass) {
        this.superClass = superClass;
    }

    @Override
    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    @Override
    public Class getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class javaClass) {
        this.javaClass = javaClass;
    }

    public List<ResourceMetaProperty> getDeclaredProperties() {
        return declaredProperties;
    }

    public void setDeclaredProperties(List<ResourceMetaProperty> declaredProperties) {
        this.declaredProperties = declaredProperties;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public Collection<? extends MetaProperty> declaredProperties() {
        return declaredProperties;
    }

    @XmlTransient
    @JsonIgnore
    @Override
    public Collection<? extends MetaProperty> getIndexedProperties() {
        return null;
    }

    @XmlTransient
    @JsonIgnore
    @Override
    public Set<? extends MetaIndex> getInvolvedIndexes(String... fields) {
        return null;
    }

    @XmlTransient
    @JsonIgnore
    @Override
    public Set<? extends MetaIndex> getInvolvedIndexes(Collection<String> fields) {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return StringUtils.isNotBlank(cacheName);
    }

    @Override
    public String cache() {
        return cacheName;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
