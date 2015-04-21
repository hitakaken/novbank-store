package com.novbank.store;

import com.novbank.store.service.metadata.Metadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by HP on 2015/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
public class MetadataTests {
    @Autowired
    Metadata metadata;

    @Test
    public void testManagers(){
        System.out.println(metadata.getSchema().countClasses());
    }
}
