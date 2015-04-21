package com.novbank.store.service.metadata.function;

/**
 * Created by HP on 2015/4/21.
 */
public interface Function<T,F> {
    String getName();
    boolean isResourceInput();
    Class[] getArgTypes();
    Class  getReturnType();
    String getCodeLanguage();
    String getCode();
    T apply(F input);
}
