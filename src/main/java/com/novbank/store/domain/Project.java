package com.novbank.store.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;

/**
 * Created by HP on 2015/4/14.
 */
@Entity
@NodeEntity(partial = true)
public class Project extends Identifiable{
    public Project() {

    }
}
