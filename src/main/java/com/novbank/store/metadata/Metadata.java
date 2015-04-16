package com.novbank.store.metadata;

import com.novbank.store.metadata.function.FunctionLibrary;
import com.novbank.store.metadata.schema.Schema;
import com.novbank.store.metadata.security.Security;

import java.io.IOException;

/**
 * Created by HP on 2015/4/16.
 */
public interface Metadata {
    void load();
    void create() throws IOException;
    void reload();
    void close();
    Schema getSchema();
    Security getSecurity();
    FunctionLibrary getFunctionLibrary();
}
