package com.novbank.store.service.metadata.support;

import com.novbank.store.service.metadata.Metadata;
import com.novbank.store.service.metadata.function.FunctionLibrary;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.security.Security;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Cao Ke on 2015/4/20.
 */
@Service
public class MetadataManager implements Metadata, InitializingBean {
    @Autowired
    private MetadataProperties properties;

    @Autowired
    private Schema schema;

    @Autowired
    private Security security;

    @Autowired
    private FunctionLibrary functions;

    @Override
    public void create() {
        functions.create();
        schema.create();
        security.create();
    }

    @Override
    public void reload() {
        functions.reload();
        schema.reload();
        security.reload();
    }

    @Override
    public void persist() {
        functions.persist();
        schema.persist();
        security.persist();
    }

    @Override
    public void close() {
        functions.close();
        schema.close();
        security.close();
    }

    @Override
    public Schema getSchema() {
        return schema;
    }

    @Override
    public Security getSecurity() {
        return security;
    }

    @Override
    public FunctionLibrary getFunctionLibrary() {
        return functions;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        create();
    }
}
