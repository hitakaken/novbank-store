package com.novbank.store.metadata.schema;

import java.util.Collection;
import java.util.Set;

/**
 * Created by HP on 2015/4/16.
 */
public interface MetaClass {
    boolean isAbstract();
    MetaClass getSuperClass();
    String getName();
    String getFullName();
    String getNameSpace();
    Collection<MetaProperty> declaredProperties();
    Collection<MetaProperty> getIndexedProperties();
    Set<Index> getInvolvedIndexes(String... fields);
    Set<Index> getInvolvedIndexes(Collection<String> fields);
    boolean isCacheable();
    String[] cacheNames();
}
