package com.novbank.store.crossstore;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareMixin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by CaoKe on 2015/4/19.
 */
@Component
@Aspect
public class ProfiledEntityAspect {
    @Autowired
    private Neo4jTemplate neo4jOps;

    @Autowired
    private MongoTemplate mongoOps;


    public void setNeo4jTemplate(Neo4jTemplate neo4jTemplate) {
        this.neo4jOps = neo4jTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoOps) {
        this.mongoOps  = mongoOps;
    }

    @DeclareMixin("(@ProfiledEntity *)")
    public ProfiledBacked createDelegate(Object instance) {
        return new DefaultProfiledEntityProxy(instance,neo4jOps, mongoOps);
    }
}
