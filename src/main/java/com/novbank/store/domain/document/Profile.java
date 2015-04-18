package com.novbank.store.domain.document;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.novbank.store.domain.AbstractProfiled;
import com.novbank.store.util.CollectionUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created by CaoKe on 2015/4/18.
 */
@Document
public class Profile extends AbstractProfiled {
    @Id
    private String id;

    private Map<String,Set<Map<String,Object>>> fields;

    public Profile(String id) {
        this.id = id;
        this.fields = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Set<Map<String, Object>>> getFields() {
        return fields;
    }

    public void setFields(Map<String, Set<Map<String, Object>>> fields) {
        this.fields = fields;
    }

    /** Implements Profiled **/
    public final static String VALUE_FIELD="_value";
    //public final static String OPTIONS_FIELD="_options";

    @Override
    public Set<String> fieldNames() {
        return fields.keySet();
    }

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options) {
        Assert.notNull(fieldName);
        if(!fields.containsKey(fieldName))
            fields.put(fieldName,new HashSet<Map<String, Object>>());
        if(options == null)   options = new HashMap<>();
        CollectionUtils.removeEmptyValueEntry(options);
        options.put(VALUE_FIELD, fieldValue);
        boolean inserted = false;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.areEqual()){
                inserted = true;
                break;
            }
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesOnlyOnRight().isEmpty()
                    && difference.entriesDiffering().size() ==1 && difference.entriesDiffering().containsKey(VALUE_FIELD)){
                entry.put(VALUE_FIELD,fieldValue);
                inserted = true;
                break;
            }
        }
        if(!inserted)
            fields.get(fieldName).add(Maps.newHashMap(options));
    }

    @Override
    public boolean containsFieldOptions(String fieldName, Map<String, Object> options) {
        if(!fields.containsKey(fieldName))
            return false;
        if(options == null)   options = new HashMap<>();
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesDiffering().isEmpty()
                    && difference.entriesOnlyOnRight().size() ==1 && difference.entriesOnlyOnRight().containsKey(VALUE_FIELD)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<Map<String, Object>, Object> getFieldValuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        Map<Map<String, Object>, Object> results = Maps.newHashMap();
        if(!fields.containsKey(fieldName))   return results;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesDiffering().isEmpty()){
                if(!strictly || (difference.entriesOnlyOnRight().size() ==1 && difference.entriesOnlyOnRight().containsKey(VALUE_FIELD)))
                    results.put(CollectionUtils.copyMapExcludeKeys(entry,Sets.newHashSet(VALUE_FIELD)), entry.get(VALUE_FIELD));
            }
        }
        return results;
    }

    @Override
    public Object getFieldValue(String fieldName, Map<String, Object> options, boolean strict) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        if(!fields.containsKey(fieldName))   return null;
        Object value = null;
        float currentSimilarity = 0;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(strict && !(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesDiffering().isEmpty()))
                continue;
            //difference.entriesInCommon()
            int lacks=0;
            int ignoreLacks = 0;
            for(Object key: difference.entriesOnlyOnLeft().keySet()){
                if(difference.entriesOnlyOnLeft().get(key) == null)
                    ignoreLacks++;
                else
                    lacks++;
            }
            int more = difference.entriesOnlyOnRight().size();
            int match = difference.entriesInCommon().size();
            int conflict = 0;
            for(Object key:difference.entriesDiffering().keySet()){
                if(options.get(key) ==null)
                    match++;
                else
                    conflict++;
            }
            float similarity = 1 - (float)(lacks +(more -1) + conflict * 3)/(float) (match + more +conflict +lacks +ignoreLacks);
            if(similarity>=currentSimilarity){
                currentSimilarity = similarity;
                value = entry.get(VALUE_FIELD);
            }
        }
        return value;
    }
}
