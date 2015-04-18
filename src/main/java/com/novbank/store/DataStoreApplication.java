package com.novbank.store;

import com.novbank.store.crossstore.CrossStoreEventListeners;
import com.novbank.store.crossstore.ProfiledNodeBacking;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;


/**
 * Created by HP on 2015/4/14.
 */
@SpringBootApplication
@EnableBatchProcessing
@EnableMongoRepositories(basePackages = "com.novbank.store.repository.mongo")
public class DataStoreApplication  extends SpringBootServletInitializer {
    public static Object[] sources = new Object[]{
            DataStoreApplication.class};

    @Value("${spring.data.neo4j.url}")
    private String url;

    @Value("${spring.data.neo4j.username}")
    private String username;

    @Value("${spring.data.neo4j.password}")
    private String password;

    @Bean
    public GraphDatabaseService graphDatabaseService() throws Exception {
        if(url.startsWith("local:")){
            return new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(url.substring(6)).newGraphDatabase();
        } else if(url.startsWith("remote:")){
            url =url.substring(7);
            if(username!=null && password!=null){
                return new SpringCypherRestGraphDatabase(url,username,password);
            }else
                return new SpringCypherRestGraphDatabase(url);
        }
        return null;
    }

    @Configuration
    @EnableNeo4jRepositories(basePackages = "com.novbank.store.repository.neo4j")
    public static class Neo4jStoreConfiguration extends Neo4jAspectConfiguration{
        public Neo4jStoreConfiguration() {
            setBasePackage("com.novbank.store.domain.graph");
        }
    }

    @Bean
    public ProfiledNodeBacking nodeProfiledBacking(MongoTemplate mongoTemplate) throws Exception {
        ProfiledNodeBacking aspect = ProfiledNodeBacking.aspectOf();
        aspect.setMongoTemplate(mongoTemplate);
        return aspect;
    }

    @Bean
    public CrossStoreEventListeners.AfterSaveEventListener afterSaveEventListener(Neo4jTemplate neo4jTemplate, MongoTemplate mongoTemplate){
        return new CrossStoreEventListeners.AfterSaveEventListener(neo4jTemplate,mongoTemplate);
    }


    public static void main(String... args) {
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(sources).showBanner(false).listeners();
    }

}
