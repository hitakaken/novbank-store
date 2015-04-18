package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.ProfiledBacked;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.annotation.Fetch;
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
    @Id
    @Fetch
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
        if(isNode())
            return (NodeBacked) this;
        return null;
    }

    @Transient
    public boolean isRelationship(){
        return (this instanceof RelationshipBacked);
    }

    public RelationshipBacked asRelationship(){
        if(isRelationship())
            return (RelationshipBacked) this;
        return null;
    }

    public boolean hasProfile(){
        return (this instanceof ProfiledBacked);
    }

    public ProfiledBacked asProfiled(){
        if(hasProfile())
            return (ProfiledBacked) this;
        return null;
    }

}
