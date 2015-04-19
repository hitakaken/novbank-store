package com.novbank.store.crossstore;

import com.novbank.store.helper.SpringApplicationContextHolder;
import com.novbank.store.repository.mongo.ProfileRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareMixin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by CaoKe on 2015/4/19.
 */
@DependsOn("springApplicationContextHolder")
@Aspect
@Configuration
public class ProfiledEntityAspect {
    @Autowired
    private transient Neo4jTemplate neo4jOps;

    @Autowired
    private transient MongoTemplate mongoOps;

    @DeclareMixin("(@ProfiledEntity *)")
    public ProfiledBacked createDelegate(Object instance) {
        return new DefaultProfiledEntityProxy(instance,neo4jOps, mongoOps);
    }



    public static ProfiledEntityAspect aspectOf() {
        return SpringApplicationContextHolder.getApplicationContext().getBean(ProfiledEntityAspect.class);
    }
}
