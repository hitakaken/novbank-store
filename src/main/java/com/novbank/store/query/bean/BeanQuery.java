package com.novbank.store.query.bean;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CaoKe on 2015/4/21.
 */
public class BeanQuery<T,S> {
    private final Function<Iterable<S>,T> selector;
    private Iterable<S> from;
    private Predicate<? super S> predicate = Predicates.alwaysTrue();
    private Ordering<S> order;

    public BeanQuery(Function<Iterable<S>,T> selector) {
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

    public BeanQuery<T,S> orderBy(Ordering<S> order){
        this.order = order;
        return this;
    }

    public BeanQuery<T,S> orderBy(Ordering<S>... orders){
        this.order = Ordering.compound(Arrays.asList(orders));
        return this;
    }

    public T execute(){
        List copy = Lists.newArrayList(from);
        if(order!=null)
            Collections.sort(copy,order);
        return selector.apply(Iterables.filter(copy, predicate));
    }
}
