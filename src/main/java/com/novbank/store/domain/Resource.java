package com.novbank.store.domain;

import org.springframework.data.mongodb.crossstore.RelatedDocument;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.Entity;

/**
 * Created by CaoKe on 2015/4/15.
 */
@Entity
@NodeEntity(partial = true)
public class Resource extends Identifiable {
    @RelatedDocument
    private Profile profile;

    public Resource() {

    }
}
