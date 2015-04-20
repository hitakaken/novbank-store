package com.novbank.store.service.metadata;

import com.novbank.store.service.metadata.function.FunctionLibrary;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.security.Security;

import java.io.IOException;

/**
 * Created by HP on 2015/4/16.
 */
public interface Metadata {
    void load();
    void create();
    void reload();
    void close();
    Schema getSchema();
    Security getSecurity();
    FunctionLibrary getFunctionLibrary();
}
