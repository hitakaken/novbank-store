package com.novbank.store.service.metadata.repository;

import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.MetaClass;

import java.util.List;

/**
 * Created by Cao Ke on 2015/4/21.
 */
public interface SchemaRepository<C extends MetaClass,P extends GlobalProperty> {
    List<C> getClasses();
    List<P> getProperties();
    C update(C classes);
    Iterable<C> update(Iterable<C> classes);
}
