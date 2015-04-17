package com.novbank.store.domain.graph;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by HP on 2015/4/15.
 */
public abstract class Identifiable implements Serializable{
    @GraphId
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public boolean isNode(){
        return (this instanceof NodeBacked);
    }

    public NodeBacked asNode(){
        if(this instanceof NodeBacked)
            return (NodeBacked) this;
        return null;
    }

    @Transient
    public boolean isRelationship(){
        return (this instanceof RelationshipBacked);
    }

    public RelationshipBacked asRelationship(){
        if(this instanceof RelationshipBacked)
            return (RelationshipBacked) this;
        return null;
    }


}