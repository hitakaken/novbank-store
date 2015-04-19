package com.novbank.store;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.service.AccountService;
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
        Account account = new Account();
        account.setName(ObjectId.get().toString());
        account.setPassword("kcao");
        account.asProfiled().putValue("QQ", "xxxxxxx");
        System.out.println(account.asNode().getNodeId());
        String profileId = account.asProfiled().profileId();
        System.out.println(profileId);
        account = accounts.save(account);
        System.out.println(account.asProfiled().value("QQ"));
        //Account newAccount = accounts.findByName("kcao");

        Account newAccount1 = template.findByIndexedValue(Account.class, "name", "kcao").singleOrNull();
        newAccount1 .setPassword("kenshin77");

        //BasicDBObject profile = mongoTemplate.findById(  newAccount1.asProfiled().profileId(),BasicDBObject.class,CrossStoreConstants.PROFILE_COLLECTION_NAME);
        DBCollection collection =  mongoTemplate.getDb().getCollection("profile");
        DBCursor cursorDoc = collection.find();

        while (cursorDoc.hasNext()) {
            System.out.println(cursorDoc.next());
        }
        //System.out.println(profile.keySet());
    }

}
