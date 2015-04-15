package com.novbank.store.domain;

import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by HP on 2015/4/15.
 */
@MappedSuperclass
public abstract class Identifiable implements Serializable{

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GraphProperty
    private String id;

    @GraphId
    private Long graphId;

    public void ensureId(){
        if(id == null)
            id = ObjectId.get().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }
}
