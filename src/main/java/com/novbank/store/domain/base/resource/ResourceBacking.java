package com.novbank.store.domain.base.resource;

import com.novbank.store.domain.graph.Resource;

/**
 * Created by CaoKe on 2015/4/19.
 */
public class ResourceBacking implements ResourceBacked {
    protected transient Resource resource;

    public ResourceBacking() { }

    public ResourceBacking(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Resource  resource() {
        return resource;
    }

    @Override
    public void resource(Resource resource) {
        this.resource = resource;
    }
}
