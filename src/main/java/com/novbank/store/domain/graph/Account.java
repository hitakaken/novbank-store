package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.ProfiledEntity;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * Created by HP on 2015/4/15.
 */
@NodeEntity
@ProfiledEntity
public class Account extends Identifiable{
    //@Indexed(unique = true)
    @GraphProperty
    private String name;

    @GraphProperty
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
