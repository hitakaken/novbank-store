package com.novbank.store.service.metadata;

import com.novbank.store.service.metadata.function.FunctionLibrary;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.security.Security;

import java.io.IOException;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface Metadata extends Persistable{
    Schema getSchema();
    Security getSecurity();
    FunctionLibrary getFunctionLibrary();
}
