package com.novbank.store.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;

/**
 * Created by HP on 2015/4/16.
 */
public class GraphDatabaseServiceFactoryBean implements FactoryBean<GraphDatabaseService> {
    private String username;
    private String url;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public GraphDatabaseService getObject() throws Exception {
        if(url.startsWith("local:")){
            return new GraphDatabaseFactory().newEmbeddedDatabase(url.substring(6));
        } else if(url.startsWith("remote:")){
            url =url.substring(7);
            if(username!=null && password!=null){
                return new SpringCypherRestGraphDatabase(url,username,password);
            }else
                return new SpringCypherRestGraphDatabase(url);
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return GraphDatabaseService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
