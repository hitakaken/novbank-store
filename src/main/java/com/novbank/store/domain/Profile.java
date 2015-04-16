package com.novbank.store.domain;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by CaoKe on 2015/4/15.
 */
//@Document
public class Profile {
    @Id
    private String id;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
