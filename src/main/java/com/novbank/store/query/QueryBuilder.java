package com.novbank.store.query;


import com.mysema.query.Tuple;

/**
 * Created by Cao Ke on 2015/4/20.
 */
public interface QueryBuilder {
    QueryBuilder from();
    QueryBuilder where();
    QueryBuilder orderBy();
    QueryBuilder groupBy();
    Object execute(Object... args);
    Object execute(Tuple args);
}

