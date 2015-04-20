package com.novbank.store;

import com.novbank.store.domain.base.resource.ResourceBacked;
import com.novbank.store.domain.entity.foaf.Document;
import com.novbank.store.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by HP on 2015/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
@WebIntegrationTest
public class CrossStoreTests {
    @Autowired
    AccountService accounts;

    @Autowired
    private Neo4jTemplate template;

    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void testEntityManager(){
        System.out.println("Start Testing!");
        Document doc = new Document();
        doc.setName("qQ");
        System.out.println(doc.getName());

        //System.out.println(profile.keySet());
    }

}
