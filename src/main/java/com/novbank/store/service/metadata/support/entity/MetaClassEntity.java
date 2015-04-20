package com.novbank.store.service.metadata.support.entity;


import com.novbank.store.service.metadata.schema.Index;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.MetaProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/20.
 */
@XmlRootElement
public class MetaClassEntity implements MetaClass {

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public MetaClass getSuperClass() {
        return null;
    }

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
    public Collection<MetaProperty> declaredProperties() {
        return null;
    }

    @Override
    public Collection<MetaProperty> getIndexedProperties() {
        return null;
    }

    @Override
    public Set<Index> getInvolvedIndexes(String... fields) {
        return null;
    }

    @Override
    public Set<Index> getInvolvedIndexes(Collection<String> fields) {
        return null;
    }

    @Override
    public boolean isCacheable() {
        return false;
    }

    @Override
    public String[] cacheNames() {
        return new String[0];
    }
}
