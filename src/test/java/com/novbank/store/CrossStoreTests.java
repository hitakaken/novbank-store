package com.novbank.store;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.novbank.store.crossstore.CrossStoreConstants;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.domain.graph.Resource;
import com.novbank.store.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.Date;

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
        /*Account account = new Account();
        account.setName("kcao");
        account.setPassword("kcao");
        account.asProfiled().put("QQ", "xxxxxxx");
        System.out.println(account.asNode().getNodeId());
        String profileId = account.asProfiled().profileId();
        System.out.println(profileId);
        account = accounts.save(account);
        //Account newAccount = accounts.findByName("kcao");*/

        Account newAccount1 = template.findByIndexedValue(Account.class, "name", "kcao").singleOrNull();
        newAccount1 .setPassword("kenshin77");

        //BasicDBObject profile = mongoTemplate.findById(  newAccount1.asProfiled().profileId(),BasicDBObject.class,CrossStoreConstants.PROFILE_COLLECTION_NAME);
        DBCollection collection =  mongoTemplate.getDb().getCollection(CrossStoreConstants.PROFILE_COLLECTION_NAME);
        DBCursor cursorDoc = collection.find();

        Resource patent = new Resource();
        Resource company = new Resource();
        patent.asNode().relateTo(company.asNode(),"assignee");
        patent.asProfiled().setFieldValue("application data",new Date());

        while (cursorDoc.hasNext()) {
            System.out.println(cursorDoc.next());
        }
        //System.out.println(profile.keySet());
    }

}
