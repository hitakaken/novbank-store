package com.novbank.store.crossstore;

import com.mongodb.DBObject;
import com.novbank.store.domain.document.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by CaoKe on 2015/4/17.
 */
@Component
public class CrossStoreListeners {

    @Component
    public class AfterNeo4jSaveEventListener implements ApplicationListener<AfterSaveEvent>{
        @Autowired
        public AfterNeo4jSaveEventListener() {     }

        @Override
        public void onApplicationEvent(AfterSaveEvent event) {
            System.out.println("Call me!");
            if((event.getEntity() instanceof ProfiledBacked)
                    && ((ProfiledBacked) event.getEntity()).profileChanged()){
                ((ProfiledBacked) event.getEntity()).persistProfile();
            }
        }
    }

    @Component
    public class ProfileStoreListener extends AbstractMongoEventListener<Profile>{
        @Autowired
        public ProfileStoreListener() {
        }

        @Override
        public void onBeforeSave(Profile source, DBObject dbo) {
            //
        }
    }
}
