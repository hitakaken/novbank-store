package com.novbank.store.service.metadata;

/**
 * Created by HP on 2015/4/21.
 */
public interface Persistable {
    void create();

    void reload();

    void persist();

    void close();
}
