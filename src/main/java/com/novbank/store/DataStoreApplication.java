package com.novbank.store;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.novbank.store.helper.DataSourceHelper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.IsNewAwareAuditingHandler;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.lifecycle.AuditingEventListener;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by HP on 2015/4/14.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableAspectJAutoProxy
@EnableMongoRepositories(basePackages = "com.novbank.store.repository.mongo")
@EnableMongoAuditing
@EnableConfigurationProperties(DataSourceHelper.Neo4jProperties.class)
@EnableCaching
public class DataStoreApplication  extends SpringBootServletInitializer {
    public static Object[] sources = new Object[]{
            DataStoreApplication.class};

    @Autowired
    private DataSourceHelper.Neo4jProperties neo4jProperties;

    @Bean(destroyMethod = "shutdown")
    public GraphDatabaseService graphDatabaseService() throws Exception {
        return DataSourceHelper.graphDatabaseService(neo4jProperties);
    }

    @Configuration
    @EnableNeo4jRepositories(basePackages = "com.novbank.store.repository.neo4j")
    @EnableTransactionManagement
    public static class Neo4jStoreConfiguration extends Neo4jAspectConfiguration{
        public Neo4jStoreConfiguration() {
            setBasePackage("com.novbank.store.domain");
        }

        /* TODO Why not work?
        @Bean
        public AuditingEventListener auditingEventListener() throws Exception {
            final IsNewAwareAuditingHandler auditingHandler =  new IsNewAwareAuditingHandler(neo4jMappingContext());
            return new AuditingEventListener(new ObjectFactory<IsNewAwareAuditingHandler>() {
                @Override
                public IsNewAwareAuditingHandler getObject() throws BeansException {
                    return auditingHandler;
                }
            });
        }*/
    }

    @Autowired
    private MongoProperties mongoProperties;

    @Autowired(required = false)
    private MongoClientOptions mongoOptions;

    @Bean
    public Mongo mongo(){
        return DataSourceHelper.mongo3(mongoProperties, mongoOptions);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public CacheManager cacheManager(){
        return new RedisCacheManager(redisTemplate);
    }

    public static void main(String... args) {
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(sources).showBanner(false).listeners();

    }

}
