package com.novbank.store.domain.base.resource;

import com.novbank.store.domain.base.profile.ProfileBacked;
import com.novbank.store.domain.graph.Resource;
import groovy.lang.GroovyObject;
import org.springframework.data.neo4j.aspects.core.NodeBacked;

/**
 * Created by CaoKe on 2015/4/19.
 */
public interface ResourceBacked {
    Resource resource();
    void resource(Resource source);
}
