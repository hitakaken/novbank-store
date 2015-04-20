package com.novbank.store.service.metadata.support;

import com.novbank.store.service.metadata.Metadata;
import com.novbank.store.service.metadata.function.FunctionLibrary;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by HP on 2015/4/20.
 */
@Service
public class MetadataManager implements Metadata{
    @Autowired
    private Schema schema;

    @Autowired
    private Security security;

    @Autowired
    private FunctionLibrary functions;

    @Override
    public void load() {

    }

    @Override
    public void create() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void close() {

    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public Security getSecurity() {
        return null;
    }

    @Override
    public FunctionLibrary getFunctionLibrary() {
        return null;
    }
}
