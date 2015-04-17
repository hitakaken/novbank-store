package com.novbank.store.domain.document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.novbank.store.crossstore.Profiled;
import org.bson.BSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/15.
 */
@Document
public class Profile implements BSONObject {
    @Id
    private String id;

    private BasicDBObject fields;

    @PersistenceConstructor
    public Profile(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BasicDBObject getFields() {
        if(null==fields)
            fields = new BasicDBObject();
        return fields;
    }

    public void setFields(Map map) {
        this.fields = new BasicDBObject(map);
    }

    public void setFields(BasicDBObject o) {
        this.fields = new BasicDBObject(o);
    }

    @Override
    public Object put(String key, Object v) {
        return getFields().put(key, v);
    }

    @Override
    public void putAll(BSONObject o) {
        getFields().putAll(o);
    }

    @Override
    public void putAll(Map m) {
        getFields().putAll(m);
    }

    @Override
    public Object get(String key) {
        return getFields().get(key);
    }

    @Override
    public Map toMap() {
        return getFields().toMap();
    }

    @Override
    public Object removeField(String key) {
        return getFields().removeField(key);
    }

    @Override
    public boolean containsKey(String key) {
        return getFields().containsKey(key);
    }

    @Override
    public boolean containsField(String s) {
        return getFields().containsField(s);
    }

    @Override
    public Set<String> keySet() {
        return getFields().keySet();
    }
}
