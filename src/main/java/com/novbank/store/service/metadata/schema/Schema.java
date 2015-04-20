package com.novbank.store.service.metadata.schema;

import java.util.Collection;
import java.util.List;

/**
 * Created by HP on 2015/4/16.
 */
public interface Schema {
    void reload();
    void create();
    void close();
    int countClasses();
    MetaClass createClass(final Class<?> iClass);
    MetaClass createClass(final String iClassName);
    MetaClass createClass(final String iClassName, final MetaClass iSuperClass);
    MetaClass createAbstractClass(final Class<?> iClass);
    MetaClass createAbstractClass(final String iClassName);
    MetaClass createAbstractClass(final String iClassName, final MetaClass iSuperClass);
    void dropClass(final String iClassName);
    boolean existsClass(final String iClassName);
    MetaClass getClass(final Class<?> iClass);
    MetaClass getClass(final String iClassName);
    MetaClass getOrCreateClass(final String iClassName);
    MetaClass getOrCreateClass(final String iClassName, final MetaClass iSuperClass);
    Collection<MetaClass> getClasses();
    List<GlobalProperty> getGlobalProperties();


}
