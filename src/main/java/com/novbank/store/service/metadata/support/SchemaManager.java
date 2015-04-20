package com.novbank.store.service.metadata.support;

import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.Schema;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Service
public class SchemaManager implements Schema{

    @Override
    public void reload() {

    }

    @Override
    public void create() {

    }

    @Override
    public void close() {

    }

    @Override
    public int countClasses() {
        return 0;
    }

    @Override
    public MetaClass createClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass createClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass createClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public void dropClass(String iClassName) {

    }

    @Override
    public boolean existsClass(String iClassName) {
        return false;
    }

    @Override
    public MetaClass getClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass getClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass getOrCreateClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass getOrCreateClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public Collection<MetaClass> getClasses() {
        return null;
    }

    @Override
    public List<GlobalProperty> getGlobalProperties() {
        return null;
    }
}
