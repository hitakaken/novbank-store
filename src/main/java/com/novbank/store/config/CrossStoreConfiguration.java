package com.novbank.store.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.neo4j.config.JtaTransactionManagerFactoryBean;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.cross_store.config.CrossStoreNeo4jConfiguration;
import org.springframework.data.neo4j.support.DelegatingGraphDatabase;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 2015/4/14.
 */
@Configuration
@EnableSpringConfigured
@AutoConfigureAfter({HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties({JpaProperties.class,Neo4jProperties.class})
public class CrossStoreConfiguration implements BeanFactoryAware {

    private static final String JTA_PLATFORM = "hibernate.transaction.jta.platform";
    private static final String[] NO_PACKAGES = new String[0];

    private ConfigurableListableBeanFactory beanFactory;

    //JPA
    /*@Autowired
    DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    @ConditionalOnMissingBean
    public JpaVendorAdapter jpaVendorAdapter() {
        AbstractJpaVendorAdapter adapter = createJpaVendorAdapter();
        adapter.setShowSql(this.jpaProperties.isShowSql());
        adapter.setDatabase(this.jpaProperties.getDatabase());
        adapter.setDatabasePlatform(this.jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(this.jpaProperties.isGenerateDdl());
        return adapter;
    }

    @Bean
    @ConditionalOnMissingBean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            JpaVendorAdapter jpaVendorAdapter) {
        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                jpaVendorAdapter, this.jpaProperties, this.persistenceUnitManager);
        //builder.setCallback(getVendorCallback());
        return builder;
    }

    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(this.jpaProperties.getHibernateProperties(this.dataSource));
        return vendorProperties;
    }

    *//*protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
        //super.customizeVendorProperties(vendorProperties);
        if (!vendorProperties.containsKey(JTA_PLATFORM)) {
            configureJtaPlatform(vendorProperties);
        }
    }*//*

    protected String[] getPackagesToScan() {
        if (AutoConfigurationPackages.has(this.beanFactory)) {
            List<String> basePackages = AutoConfigurationPackages.get(this.beanFactory);
            return basePackages.toArray(new String[basePackages.size()]);
        }
        return NO_PACKAGES;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    //@DependsOn({"mongoDocumentBacking","neo4jNodeBacking"})
    public EntityManagerFactory entityManagerFactory(
            EntityManagerFactoryBuilder factoryBuilder) {
        Map<String, Object> vendorProperties = getVendorProperties();
        //customizeVendorProperties(vendorProperties);
        return factoryBuilder.dataSource(this.dataSource).packages(getPackagesToScan())
                .properties(vendorProperties).build().getObject();
    }*/




    //Neo4j
    @Autowired
    private Neo4jProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public GraphDatabaseService graphDatabaseService(){
        return new GraphDatabaseFactory().newEmbeddedDatabase(properties.getStoreDirectory());
    }

    @Bean
    public CrossStoreNeo4jConfiguration crossStoreNeo4jConfiguration(EntityManagerFactory entityManagerFactory, GraphDatabaseService graphDatabaseService){
        CrossStoreNeo4jConfiguration configuration = new CrossStoreNeo4jConfiguration();
        configuration.setEntityManagerFactory(entityManagerFactory);
        configuration.setGraphDatabaseService(graphDatabaseService);
        return configuration;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
