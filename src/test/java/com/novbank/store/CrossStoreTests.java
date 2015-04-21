package com.novbank.store;

import com.novbank.store.domain.entity.foaf.Document;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.domain.graph.Resource;
import com.novbank.store.service.account.AccountService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cao Ke on 2015/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
@WebIntegrationTest
public class CrossStoreTests {
    @Autowired
    AccountService accounts;

    @Autowired
    private Neo4jTemplate neo4jOps;

    @Autowired
    private MongoTemplate mongoOps;



    @Test
    public void testEntityManager(){
        Resource doc = new Resource();
        doc.setUrl("foaf:Document:kcao_first_blog");
        doc.asProfiled().save();
        //accounts.save(account);
        //System.out.println(profile.keySet());
    }

}
