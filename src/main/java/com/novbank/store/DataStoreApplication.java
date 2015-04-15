package com.novbank.store;

import com.mongodb.Mongo;
import com.novbank.store.config.CrossStoreConfiguration;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import java.io.IOException;

/**
 * Created by HP on 2015/4/14.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.novbank.store.domain")
@EnableJpaRepositories(basePackages = "com.novbank.store.repository")
public class DataStoreApplication  extends SpringBootServletInitializer {
    public static Object[] sources = new Object[]{
            DataStoreApplication.class,
            DataSourceAutoConfiguration.class,
            CrossStoreConfiguration.class};



    public static void main(String... args) {
        SpringApplication.run(sources, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(sources).showBanner(false).listeners();
    }

}
