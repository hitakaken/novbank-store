package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.ProfiledNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * Created by HP on 2015/4/15.
 */
@NodeEntity
@ProfiledNode
public class Account extends Identifiable{
    @Indexed(unique = true)
    @GraphProperty
    @Fetch
    private String name;

    @GraphProperty
    @Fetch
    private String password;

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
