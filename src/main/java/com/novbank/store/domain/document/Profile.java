package com.novbank.store.domain.document;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mongodb.DBRef;
import com.novbank.store.domain.base.profile.AbstractProfile;
import com.novbank.store.util.CollectionUtils;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import java.util.*;

/**
 * 档案
 *
 * Created by CaoKe on 2015/4/18.
 */
@Document
public class Profile extends AbstractProfile {
    public Profile() {
    }

    public Profile(String id) {
        this.id = id;
        this.fields = new HashMap<>();
    }

    @Id
    private String id;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date lastModified;

    @Version
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date  lastModified) {
        this.lastModified = lastModified;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }



    /** Implements Profiled **/
    /**
     *  实现 Profiled 的 Java 数据结构
     */
    private Map<String,Set<Map<String,Object>>> fields;

    public Map<String, Set<Map<String, Object>>> getFields() {
        return fields;
    }

    public void setFields(Map<String, Set<Map<String, Object>>> fields) {
        this.fields = fields;
    }

    public final static String VALUE_FIELD="_value";
    public final static String TIMESTAMP_FIELD="_timestamp";
    protected final static Set<String> IGNORE_COMPARE_FIELDS = Sets.newHashSet(VALUE_FIELD,TIMESTAMP_FIELD);
    protected final static Set<String> EXCLUDE_OPTION_FIELDS = Sets.newHashSet(VALUE_FIELD);
    //public final static String OPTIONS_FIELD="_options";

    @Override
    public Set<String> fieldNames() {
        return fields.keySet();
    }

    @Transient
    protected transient boolean changed = false;

    @Override
    public void putValue(String fieldName, Object fieldValue, Map<String, Object> options, boolean overwrite) {
        Assert.notNull(fieldName);
        if(!fields.containsKey(fieldName))
            fields.put(fieldName,new HashSet<Map<String, Object>>());
        if(options == null)   options = new HashMap<>();
        CollectionUtils.removeEmptyValueEntry(options);
        options.put(VALUE_FIELD, fieldValue);
        if(!options.containsKey(TIMESTAMP_FIELD))
            options.put(TIMESTAMP_FIELD,System.currentTimeMillis());
        boolean replaced = false;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.entriesOnlyOnLeft().isEmpty() && difference.entriesOnlyOnRight().isEmpty()
                    && Sets.difference(difference.entriesDiffering().keySet(), IGNORE_COMPARE_FIELDS).size()==0){
                if(difference.entriesDiffering().containsKey(VALUE_FIELD)){
                    entry.put(VALUE_FIELD,fieldValue);
                    entry.put(TIMESTAMP_FIELD,options.get(TIMESTAMP_FIELD));
                    changed = true;
                }
                replaced = true;
                break;
            }

        }
        if(!replaced) {
            fields.get(fieldName).add(Maps.newHashMap(options));
            changed = true;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Map<String, Object> options, boolean strictly) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        Map<Map<String, Object>, Object> results = Maps.newHashMap();
        if(!fields.containsKey(fieldName))   return results;
        for(Map<String,Object> entry: fields.get(fieldName)){
            MapDifference difference = Maps.difference(options, entry);
            if(difference.entriesOnlyOnLeft().isEmpty() && Sets.difference(difference.entriesDiffering().keySet(), IGNORE_COMPARE_FIELDS).size()==0){
                if(!strictly || Sets.difference(difference.entriesOnlyOnRight().keySet(),IGNORE_COMPARE_FIELDS).size()==0)
                    results.put(CollectionUtils.copyMapExcludeKeys(entry, EXCLUDE_OPTION_FIELDS), entry.get(VALUE_FIELD));
            }
        }
        return results;
    }

    @Override
    public Map<Map<String, Object>, Object> valuesWithOptions(String fieldName, Predicate predicate) {
        Assert.notNull(fieldName);
        if(predicate == null)   predicate = Predicates.alwaysTrue();
        final Map<Map<String, Object>, Object> results = Maps.newHashMap();
        if(!fields.containsKey(fieldName))  return results;
        for(Map<String,Object> entry: fields.get(fieldName)){
            if(predicate.apply(entry))
                results.put(CollectionUtils.copyMapExcludeKeys(entry, EXCLUDE_OPTION_FIELDS), entry.get(VALUE_FIELD));
        }
        return results;
    }

    public final static double LOWEST_SIMILARITY = 0.0;
    public final static boolean USE_LATEST = true;

    @Override
    public Object value(String fieldName, Map<String, Object> options, boolean strict) {
        Assert.notNull(fieldName);
        if(options == null)   options = new HashMap<>();
        if(!fields.containsKey(fieldName))   return null;
        Object value = null;
        double currentSimilarity = LOWEST_SIMILARITY;
        for(Map<String,Object> entry: fields.get(fieldName)){
            double similarity = CollectionUtils.calculateSimilarity(options,entry, IGNORE_COMPARE_FIELDS, strict, true, 1.0, 0.2, 3.0);
            if(similarity>currentSimilarity || (USE_LATEST && similarity == currentSimilarity)){
                currentSimilarity = similarity;
                value = entry.get(VALUE_FIELD);
            }
        }
        return value;
    }

    /**
     * 记录图模型信息
     */
    protected GraphInfo graph;

    public GraphInfo getGraph() {
        return graph;
    }

    public void setGraph(GraphInfo graph) {
        this.graph = graph;
        this.changed = true;
    }



}

