package com.novbank.store.service.metadata.function;

import com.novbank.store.service.metadata.Persistable;

import java.util.Set;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface FunctionLibrary extends Persistable {

    Set<String> getFunctionNames();

    Function getFunction(String iName, Object... args);

    Function getResourceInputFunction(String iName, Object... args);

    Function createFunction(String name,boolean isResourceInput, Class[] argTypes,Class returnType, String codeLanguage, String code);
}
