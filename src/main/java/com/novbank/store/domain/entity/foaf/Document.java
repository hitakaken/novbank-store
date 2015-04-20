package com.novbank.store.domain.entity.foaf;

import com.novbank.store.domain.base.resource.ResourceEntity;

/**
 * Created by CaoKe on 2015/4/19.
 */
@ResourceEntity
public class Document {
    private String name;

    public Document() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
