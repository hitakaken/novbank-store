package com.novbank.store.domain;

import org.bson.types.ObjectId;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by HP on 2015/4/15.
 */
@MappedSuperclass
public abstract class Identifiable implements Serializable{
    @GraphProperty
    @Indexed
    @Id
    private String guid;

    @GraphId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PrePersist
    public void ensureGUID(){
        if(guid == null)
            guid = ObjectId.get().toString();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
