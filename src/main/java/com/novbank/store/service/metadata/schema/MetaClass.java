package com.novbank.store.service.metadata.schema;

import java.util.Collection;
import java.util.Set;

/**
 *
 *
 * Created by Cao Ke on 2015/4/16.
 */
public interface MetaClass {
    String getName();
    String getNamespace();
    String getJavaClassName();
    Class getJavaClass();
    boolean isAbstract();
    MetaClass getSuperClass();
    Collection<? extends MetaProperty> declaredProperties();
    Collection<? extends MetaProperty> getIndexedProperties();
    Set<? extends MetaIndex> getInvolvedIndexes(String... fields);
    Set<? extends MetaIndex> getInvolvedIndexes(Collection<String> fields);
    boolean isCacheable();
    String cache();
}
