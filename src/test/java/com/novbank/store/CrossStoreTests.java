package com.novbank.store;

import com.novbank.store.crossstore.ProfileBacked;
import com.novbank.store.domain.document.Profile;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
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
    AccountService accounts;

    @Test
    public void testEntityManager(){
        for(EntityType type: em.getMetamodel().getEntities()){
            System.out.println(type.getName());
        }
        Account account = new Account();
        account.setName("kcao");
        account.setPassword("kcao");
        Profile profile = ((ProfileBacked)account).profile();
        System.out.println(profile.isAsNode());
        account = accounts.save(account);
        System.out.println(account.getClass());
    }

}
