package com.novbank.store.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by HP on 2015/4/15.
 */
@Entity
//@Table( name="Account", uniqueConstraints= @UniqueConstraint(columnNames={"name"}))
@NodeEntity(partial = true)
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @TableGenerator(name = "id_gen", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_GEN", allocationSize = 1)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
