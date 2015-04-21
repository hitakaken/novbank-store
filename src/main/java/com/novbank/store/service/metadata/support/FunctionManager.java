package com.novbank.store.service.metadata.support;

import com.novbank.store.service.metadata.function.Function;
import com.novbank.store.service.metadata.function.FunctionLibrary;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Service
public class FunctionManager implements FunctionLibrary{

    @Override
    public void create() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void persist() {

    }

    @Override
    public void close() {

    }

    @Override
    public Set<String> getFunctionNames() {
        return null;
    }

    @Override
    public Function getFunction(String iName, Object... args) {
        return null;
    }

    @Override
    public Function getResourceInputFunction(String iName, Object... args) {
        return null;
    }

    @Override
    public Function createFunction(String name, boolean isResourceInput, Class[] argTypes, Class returnType, String codeLanguage, String code) {
        return null;
    }
}
