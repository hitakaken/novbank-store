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
    public final static Function<Iterator<?>, List<?>> NoChangeToListSelector =
            new Function<Iterator<?>,List<?>>() {
                @Nullable  @Override
                public List<?> apply(Iterator<?> input) {
                    return Lists.newArrayList(input);
                }
            };

    public final static Function<Iterator<?>, Set<?>> NoChangeToSetSelector =
            new Function<Iterator<?>,Set<?>>() {
                @Nullable  @Override
                public Set<?> apply(Iterator<?> input) {
                    return Sets.newHashSet(input);
                }
            };

    public static BeanQuery select(){
        return new BeanQuery(NoChangeToListSelector);
    }

    public static PropertyGetter value(String propertyName){
        return null;
    }
}
