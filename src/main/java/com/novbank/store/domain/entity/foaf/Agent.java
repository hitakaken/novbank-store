package com.novbank.store.domain.entity.foaf;

import com.novbank.store.domain.base.resource.ResourceEntity;
import com.novbank.store.domain.base.resource.ResourceProperty;

/**
 * Created by Cao Ke on 2015/4/20.
 */
@ResourceEntity
public class Agent {
    @ResourceProperty
    protected String name;

    public Agent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
