package com.novbank.store.domain.base.profile;

import com.novbank.store.domain.graph.Identifiable;
import com.novbank.store.helper.SpringApplicationContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareMixin;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.aspects.core.GraphBacked;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.support.Neo4jTemplate;

/**
 *
 *
 * Created by CaoKe on 2015/4/19.
 */
@DependsOn("springApplicationContextHolder")
@Aspect
@Configuration
public class ProfileEntityAspect {
    @Autowired
    private Neo4jTemplate neo4jOps;

    @Autowired
    private MongoTemplate mongoOps;

    @DeclareMixin("(@ProfileEntity *)")
    public ProfileBacked createDelegate(Object instance) {
        return new MongoProfileBacking(instance, mongoOps);
    }

    public static ProfileEntityAspect aspectOf() {
        return SpringApplicationContextHolder.getApplicationContext().getBean(ProfileEntityAspect.class);
    }
}
