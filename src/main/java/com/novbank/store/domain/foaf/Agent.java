package com.novbank.store.domain.foaf;

import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;

/**
 * Created by HP on 2015/4/14.
 */
@Entity
@NodeEntity(partial = true)
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Agent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
