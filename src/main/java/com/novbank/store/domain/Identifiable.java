package com.novbank.store.domain;

/*
import org.bson.types.ObjectId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;

import javax.persistence.*;
*/
import java.io.Serializable;

/**
 * Created by HP on 2015/4/15.
 */
//@MappedSuperclass
public abstract class Identifiable implements Serializable{
   /* @GraphProperty
    @Indexed
    @Column(name="guid",unique = true)
    private String guid;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @TableGenerator(name = "id_gen", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GEN", allocationSize = 1)
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
    }*/

}
