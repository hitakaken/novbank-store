package com.novbank.store.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by HP on 2015/4/14.
 */
@ConfigurationProperties(prefix = "spring.data.neo4j")
public class Neo4jProperties {
    private String storeDirectory;

    public Neo4jProperties() {
    }

    public String getStoreDirectory() {
        return storeDirectory;
    }

    public void setStoreDirectory(String storeDirectory) {
        this.storeDirectory = storeDirectory;
    }
}
