package com.novbank.store.query.bean;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

import java.util.Collections;
import java.util.Iterator;

/**
 * Created by CaoKe on 2015/4/21.
 */
public class BeanQuery<T,S> {
    private final Function<Iterator<S>,T> selector;
    private Iterable<S> from;
    private Predicate<? super S> predicate = Predicates.alwaysTrue();

    public BeanQuery(Function<Iterator<S>,T> selector) {
        this.selector = selector;
    }

    public BeanQuery<T,S> from(Iterable<S> from) {
        this.from = from;
        return this;
    }

    public BeanQuery<T,S> from( S from){
        this.from = Collections.singleton(from);
        return this;
    }

    public BeanQuery<T,S> where(Predicate<? super S> predicate){
        this.predicate = predicate;
        return this;
    }

    public BeanQuery<T,S> where(Predicate<? super S>... predicates){
        this.predicate = Predicates.and(predicates);
        return this;
    }

    public T execute(){
        return selector.apply(Iterators.filter(from.iterator(), predicate));
    }
}
