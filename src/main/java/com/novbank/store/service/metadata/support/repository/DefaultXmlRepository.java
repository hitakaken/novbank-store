package com.novbank.store.service.metadata.support.repository;

import com.google.common.collect.Lists;
import com.novbank.store.service.metadata.repository.SchemaRepository;
import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.support.entity.ResourceGlobalProperty;
import com.novbank.store.service.metadata.support.entity.ResourceMetaClass;

import java.io.File;
import java.util.List;

/**
 * Created by CaoKe on 2015/4/20.
 */
public class DefaultXmlRepository implements SchemaRepository<ResourceMetaClass,ResourceGlobalProperty>{
    private File baseFile;
    private boolean singleSchemaFile;

    public DefaultXmlRepository(File baseFile, boolean singleSchemaFile) {
        this.baseFile = baseFile;
        this.singleSchemaFile = singleSchemaFile;
    }

    @Override
    public List<ResourceMetaClass> getClasses() {
        List<ResourceMetaClass> results = Lists.newArrayList();
        return results;
    }

    @Override
    public List<ResourceGlobalProperty> getProperties() {
        List<ResourceGlobalProperty> results = Lists.newArrayList();
        return results;
    }

    @Override
    public ResourceMetaClass update(ResourceMetaClass classes) {
        return null;
    }

    @Override
    public Iterable<ResourceMetaClass> update(Iterable<ResourceMetaClass> classes) {
        return null;
    }
}
