package com.novbank.store.domain.document;

import com.google.common.collect.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by CaoKe on 2015/4/18.
 */
@Document
public class Profile implements Profiled{
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



}
