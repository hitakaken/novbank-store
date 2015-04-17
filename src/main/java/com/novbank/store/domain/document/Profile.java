package com.novbank.store.domain.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by CaoKe on 2015/4/15.
 */
@Document
@CompoundIndex(name = "graph_index", def = "{'graphId': 1, 'nodeProfile': -1}", background = true, dropDups = true,   sparse = true, unique = true)
public class Profile {
    @Id
    private String id;

    @NotNull
    @Indexed
    private Long graphId;

    private boolean asNode;

    public Profile() {

    }

    @PersistenceConstructor
    public Profile(boolean asNode) {
        this.asNode = asNode;
    }

    @PersistenceConstructor
    public Profile(long graphId, boolean asNode) {
        this.graphId = graphId;
        this.asNode = asNode;
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

    public void setGraphId(long graphId) {
        this.graphId = graphId;
    }

    public boolean isAsNode() {
        return asNode;
    }

    public void setAsNode(boolean asNode) {
        this.asNode = asNode;
    }
}
