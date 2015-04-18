package com.novbank.store.util;

import com.google.common.collect.Maps;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/18.
 */
public class CollectionUtils {
    public static <K,V> void removeEmptyValueEntry(final Map<K,V> map){
        Set<K> keyToDelete = new HashSet<>();
        if(map == null) return;
        for(K key : map.keySet())
            if(map.get(key) == null)
                keyToDelete.add(key);
        for(K key:keyToDelete)
            map.remove(key);
    }

    public static <K,V> Map<K,V> copyMapExcludeKeys(Map<K,V> map, Set<K> excludeKeys){
        Map<K,V> copy = Maps.newHashMap();
        if(map == null) return copy;
        for(K key:map.keySet()){
            if(!excludeKeys.contains(key))
                copy.put(key,map.get(key));
        }
        return copy;
    }
}
