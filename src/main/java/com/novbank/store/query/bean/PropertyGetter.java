package com.novbank.store.query.bean;

import com.google.common.base.Predicate;
import com.google.common.collect.Ordering;

/**
 * Created by CaoKe on 2015/4/21.
 */
public class PropertyGetter<S> extends Ordering<S> implements Predicate<S> {
    private final String propertyName;
    public PropertyGetter(String propertyName){
        this.propertyName = propertyName;
    }



    @Override
    public boolean apply(S input) {
        return false;
    }

    public Predicate isNotNull(){
        return null;
    }

    public Predicate isNull(){
        return null;
    }

    public Predicate equal(final Object other){
        return null;
    }


    @Override
    public int compare(S left, S right) {
        return 0;
    }

    public Ordering<S> asc(){
        return this;
    }

    public Ordering<S> desc(){
        return this;
    }
}
