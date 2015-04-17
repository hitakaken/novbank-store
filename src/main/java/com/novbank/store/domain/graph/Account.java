package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.Profiled;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by HP on 2015/4/15.
 */
@NodeEntity
@Profiled
public class Account extends Identifiable{

    @NotNull
    private String name;

    @NotNull
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
