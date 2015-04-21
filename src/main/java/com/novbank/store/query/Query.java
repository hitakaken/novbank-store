package com.novbank.store.query;

/**
 * Created by CaoKe on 2015/4/21.
 */
public interface Query<T> {
    T execute(Object... args);
}
