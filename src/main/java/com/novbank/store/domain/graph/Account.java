package com.novbank.store.domain.graph;

import com.novbank.store.domain.base.profile.ProfileEntity;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * Created by Cao Ke on 2015/4/15.
 */
@NodeEntity
@ProfileEntity
public class Account extends Identifiable{
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
