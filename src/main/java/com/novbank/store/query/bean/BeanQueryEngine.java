package com.novbank.store.query.bean;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/21.
 */
public class BeanQueryEngine {
    public final static Function<Iterable<?>, List<?>> NoChangeToListSelector =
            new Function<Iterable<?>,List<?>>() {
                @Nullable  @Override
                public List<?> apply(Iterable<?> input) {
                    return Lists.newArrayList(input);
                }
            };

    public final static Function<Iterable<?>, Set<?>> NoChangeToSetSelector =
            new Function<Iterable<?>,Set<?>>() {
                @Nullable  @Override
                public Set<?> apply(Iterable<?> input) {
                    return Sets.newLinkedHashSet(input);
                }
            };

    public static BeanQuery<?,?> select(){
        return new BeanQuery(NoChangeToListSelector);
    }

    public static PropertyGetter value(String propertyName){
        return null;
    }
}
