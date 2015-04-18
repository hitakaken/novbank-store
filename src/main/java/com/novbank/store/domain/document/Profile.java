package com.novbank.store.domain.document;

import com.novbank.store.domain.AbstractProfiled;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
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
    //public final static String OPTIONS_FIELD="_options";

    @Override
    public Set<String> fieldNames() {
        return fields.keySet();
    }
}
