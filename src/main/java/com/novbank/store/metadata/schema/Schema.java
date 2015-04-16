package com.novbank.store.metadata.schema;

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
    SchemaClass createClass(final Class<?> iClass);
    SchemaClass createClass(final String iClassName);
    SchemaClass createClass(final String iClassName, final SchemaClass iSuperClass);
    SchemaClass createAbstractClass(final Class<?> iClass);
    SchemaClass createAbstractClass(final String iClassName);
    SchemaClass createAbstractClass(final String iClassName, final SchemaClass iSuperClass);
    void dropClass(final String iClassName);
    boolean existsClass(final String iClassName);
    SchemaClass getClass(final Class<?> iClass);
    SchemaClass getClass(final String iClassName);
    SchemaClass getOrCreateClass(final String iClassName);
    SchemaClass getOrCreateClass(final String iClassName, final SchemaClass iSuperClass);
    Collection<SchemaClass> getClasses();
    List<GlobalProperty> getGlobalProperties();


}
