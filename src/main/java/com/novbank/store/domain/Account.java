package com.novbank.store.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Created by HP on 2015/4/15.
 */
@Entity
//@Table( name="Account", uniqueConstraints= @UniqueConstraint(columnNames={"name"}))
@NodeEntity(partial = true)
public class Account extends Identifiable{
    @NotNull
    private String name;

    @NotNull
    private String password;

    public Account() {

    }

    @PersistenceConstructor
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
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
