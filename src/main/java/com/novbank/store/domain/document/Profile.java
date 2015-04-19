package com.novbank.store.domain.document;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.novbank.store.domain.base.AbstractProfiled;
import com.novbank.store.util.CollectionUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    protected final static Set<String> IGNORE_FIELDS= Sets.newHashSet(VALUE_FIELD);

    //public final static String OPTIONS_FIELD="_options";

    @Override
    public Set<String> fieldNames() {
        return fields.keySet();
    }

    protected transient boolean changed = false;

    @Override
    public void setFieldValue(String fieldName, Object fieldValue, Map<String, Object> options, boolean overwrite) {
        Assert.notNull(fieldName);
        if(!fields.containsKey(fieldName))
            fields.put(fieldName,new HashSet<Map<String, Object>>());
        if(options == null)   options = new HashMap<>();
        CollectionUtils.removeEmptyValueEntry(options);
        options.put(VALUE_FIELD, fieldValue);
        boolean replaced = false;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.areEqual()){
                replaced = true;
                break;
            }
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesOnlyOnRight().isEmpty()
                    && difference.entriesDiffering().size() ==1 && difference.entriesDiffering().containsKey(VALUE_FIELD)){
                entry.put(VALUE_FIELD,fieldValue);
                replaced = true;
                changed = true;
                break;
            }
        }
        if(!replaced)
            fields.get(fieldName).add(Maps.newHashMap(options));
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public Map<Map<String, Object>, Object> fieldValuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        Map<Map<String, Object>, Object> results = Maps.newHashMap();
        if(!fields.containsKey(fieldName))   return results;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesDiffering().isEmpty()){
                if(!strictly || (difference.entriesOnlyOnRight().size() ==1 && difference.entriesOnlyOnRight().containsKey(VALUE_FIELD)))
                    results.put(CollectionUtils.copyMapExcludeKeys(entry,IGNORE_FIELDS), entry.get(VALUE_FIELD));
            }
        }
        return results;
    }

    public final static double LOWEST_SIMILARITY = 0.0;
    public final static boolean USE_LATEST = true;

    @Override
    public Object fieldValue(String fieldName, Map<String, Object> options, boolean strict) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        if(!fields.containsKey(fieldName))   return null;
        Object value = null;
        double currentSimilarity = LOWEST_SIMILARITY;
        for(Map<String,Object> entry: fields.get(fieldName)){
            double similarity = CollectionUtils.calculateSimilarity(options,entry,IGNORE_FIELDS, strict, true, 1.0, 0.2, 3.0);
            if(similarity>currentSimilarity || (USE_LATEST && similarity == currentSimilarity)){
                currentSimilarity = similarity;
                value = entry.get(VALUE_FIELD);
            }
        }
        return value;
    }
}
