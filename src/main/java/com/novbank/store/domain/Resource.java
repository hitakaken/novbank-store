package com.novbank.store.domain;


import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;

/**
 * Created by CaoKe on 2015/4/15.
 */
@Entity
@NodeEntity(partial = true)
public class Resource  {
   /* @RelatedDocument
    private Profile profile;*/

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @TableGenerator(name = "id_gen", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GEN", allocationSize = 1)
    private Long id;

    public Resource() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
