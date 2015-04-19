package com.novbank.store.util;

import com.google.common.collect.MapDifference;
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

    public static <K,V1,V2> double calculateSimilarity(Map<K,V1> left, Map<K,V2> right, Set<K> ignoreKeys,
                                                      boolean strict, boolean ignoreNull, double leftMoreFactor, double rightMoreFactor, double conflictFactor){
        MapDifference<K,?> difference = Maps.difference(left, right);
        double conflict = 0;
        double leftMore = 0;
        double rightMore = 0;
        double ignores = 0;
        double match = 0;
        for(K key: difference.entriesDiffering().keySet()){
            if(ignoreKeys.contains(key)){
                ignores++;continue;
            }
            if(ignoreNull && left.get(key)==null){
                match++; continue;
            }
            if(ignoreNull && right.get(key) == null){
                leftMore++;continue;
            }
            conflict++;
        }
        for(K key : difference.entriesOnlyOnLeft().keySet()){
            if(ignoreKeys.contains(key)){
                ignores++;continue;
            }
            if(ignoreNull && left.get(key) == null){

            }else
                leftMore++;
        }
        for(K key : difference.entriesOnlyOnRight().keySet()){
            if(ignoreKeys.contains(key)){
                ignores++;continue;
            }
            if(ignoreNull && right.get(key) == null){

            }else
                rightMore++;
        }
        match += difference.entriesInCommon().size();
        if(conflict>0 && strict)  return Double.MIN_VALUE;
        return  1 -
                (leftMore * leftMoreFactor + rightMore * rightMoreFactor + conflict* conflictFactor) /
                        (1 + ignores + match + leftMore + rightMore + conflict);
    }
}
