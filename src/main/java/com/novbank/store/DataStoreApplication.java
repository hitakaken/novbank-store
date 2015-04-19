package com.novbank.store;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.novbank.store.helper.DataSourceHelper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;


/**
 * Created by HP on 2015/4/14.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "com.novbank.store.repository.mongo")
@EnableConfigurationProperties(DataSourceHelper.Neo4jProperties.class)
public class DataStoreApplication  extends SpringBootServletInitializer {
    public static Object[] sources = new Object[]{
            DataStoreApplication.class};

    @Autowired
    private DataSourceHelper.Neo4jProperties neo4jProperties;

    @Bean
    public GraphDatabaseService graphDatabaseService() throws Exception {
        return DataSourceHelper.graphDatabaseService(neo4jProperties);
    }

    @Autowired
    private MongoProperties mongoProperties;

    @Autowired(required = false)
    private MongoClientOptions mongoOptions;

    @Bean
    public Mongo mongo(){
        return DataSourceHelper.mongo3(mongoProperties,mongoOptions);
    }

    @Configuration
    @EnableNeo4jRepositories(basePackages = "com.novbank.store.repository.neo4j")
    public static class Neo4jStoreConfiguration extends Neo4jAspectConfiguration{
        public Neo4jStoreConfiguration() {
            setBasePackage("com.novbank.store.domain.graph");
        }
    }

    /*@Bean
    public ProfiledNodeAspect nodeProfiledAspect(MongoTemplate mongoTemplate) throws Exception {
        ProfiledNodeAspect aspect = new ProfiledNodeAspect();
        aspect.setMongoTemplate(mongoTemplate);
        return aspect;
    }*/

    /*@Bean
    public CrossStoreEventListeners.AfterSaveEventListener afterSaveEventListener(Neo4jTemplate neo4jTemplate, MongoTemplate mongoTemplate){
        return new CrossStoreEventListeners.AfterSaveEventListener(neo4jTemplate,mongoTemplate);
    }*/


    public static void main(String... args) {
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(sources).showBanner(false).listeners();
    }

}
