package com.novbank.store.domain.entity.foaf;

import com.novbank.store.domain.base.resource.ResourceEntity;
import com.novbank.store.domain.base.resource.ResourceProperty;
import com.novbank.store.domain.base.resource.ResourceRelation;

/**
 * Created by CaoKe on 2015/4/19.
 */
@ResourceEntity
public class Document {
    @ResourceProperty
    private String name;

    @ResourceRelation
    private Agent uploader;

    public Document() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agent getUploader() {
        return uploader;
    }

    public void setUploader(Agent uploader) {
        this.uploader = uploader;
    }
}
