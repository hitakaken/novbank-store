package com.novbank.store.service.metadata.schema;

import com.novbank.store.service.metadata.Persistable;

import java.util.Collection;
import java.util.List;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface Schema<C extends MetaClass>  extends Persistable {
    int countClasses();
    C createClass(final Class<?> iClass);
    C createClass(final String iClassName);
    C createClass(final String iClassName, final C iSuperClass);
    C createAbstractClass(final Class<?> iClass);
    C createAbstractClass(final String iClassName);
    C createAbstractClass(final String iClassName, final C iSuperClass);
    void dropClass(final String iClassName);
    boolean existsClass(final String iClassName);
    C getClass(final Class<?> iClass);
    C getClass(final String iClassName);
    C getOrCreateClass(final String iClassName);
    C getOrCreateClass(final String iClassName, final C iSuperClass);
    Collection<C> getClasses();
    List<GlobalProperty> getGlobalProperties();
}
