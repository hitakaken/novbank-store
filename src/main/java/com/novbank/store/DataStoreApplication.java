package com.novbank.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by HP on 2015/4/14.
 */
@SpringBootApplication
public class DataStoreApplication  extends SpringBootServletInitializer {
    public static Object[] sources = new Object[]{
            //CrossStoreConfiguration.class,
            DataStoreApplication.class};

    @EnableJpaRepositories(basePackages = "com.novbank.store.repository")
    @EntityScan(basePackages = "com.novbank.store.domain")
    @EnableTransactionManagement
    @ImportResource("classpath:spring/neo4j.xml")
    public static class CrossStoreConfiguration{

    }

    public static void main(String... args) {
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(sources).showBanner(false).listeners();
    }

}
