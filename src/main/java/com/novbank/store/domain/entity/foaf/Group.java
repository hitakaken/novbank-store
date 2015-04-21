package com.novbank.store.domain.entity.foaf;

import com.novbank.store.domain.base.resource.ResourceEntity;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Created by Cao Ke on 2015/4/20.
 */
@ResourceEntity
public class Group extends Agent{
    @RelatedTo(type = "member",direction = Direction.OUTGOING)
    protected Set<Agent> members;
}
