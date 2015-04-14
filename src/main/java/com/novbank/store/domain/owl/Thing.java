package com.novbank.store.domain.owl;

import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;

/**
 * Created by HP on 2015/4/14.
 */
@Entity
@NodeEntity(partial = true)
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Thing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
