package com.novbank.store;

import com.novbank.store.domain.Account;
import com.novbank.store.repository.AccountRepository;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.neo4j.aspects.support.node.Neo4jNodeBacking;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;

/**
 * Created by HP on 2015/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
@WebIntegrationTest
public class CrossStoreTests {
    @Autowired
    EntityManager em;

    @Autowired
    AccountRepository accounts;

    @Autowired(required = false)
    private EntityManagerFactory entityManagerFactory;

    @Autowired(required = false)
    private HibernateEntityManagerFactory hibernateEntityManagerFactory;

    @Test
    public void testEntityManager(){
        for(EntityType type: em.getMetamodel().getEntities()){
            System.out.println(type.getName());
        }
        Account account = new Account();
        account.setName("kcao");
        account.setPassword("kcao");
        account = accounts.save(account);
        System.out.println(account.getClass());
    }

}
