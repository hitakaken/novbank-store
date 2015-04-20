package com.novbank.store.crossstore;

import com.mongodb.DBObject;
import com.novbank.store.domain.base.profile.ProfileBacked;
import com.novbank.store.domain.document.Profile;
import com.novbank.store.domain.graph.Identifiable;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.data.neo4j.lifecycle.BeforeSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by CaoKe on 2015/4/17.
 */
@Component
public class CrossStoreListeners {
    @Component
    public class BeforeNeo4jSaveEventListener implements ApplicationListener<BeforeSaveEvent>{
        @Autowired
        public BeforeNeo4jSaveEventListener() {     }

        @Override
        public void onApplicationEvent(BeforeSaveEvent event) {
            if(event.getEntity() instanceof Identifiable){
                Identifiable entity = (Identifiable) event.getEntity();
                entity.setVersion(entity.getVersion() == null ? 0 : entity.getVersion() + 1);
                DateTime current = DateTime.now(DateTimeZone.forID("GMT+8"));
                if(entity.getCreateTime() == null)
                    entity.setCreateTime(current);
                entity.setLastModified(current);
            }
        }
    }

    @Component
    public class AfterNeo4jSaveEventListener implements ApplicationListener<AfterSaveEvent>{
        @Autowired
        public AfterNeo4jSaveEventListener() {     }

        @Override
        public void onApplicationEvent(AfterSaveEvent event) {
            System.out.println("Call me!");
            if((event.getEntity() instanceof ProfileBacked)
                    && ((ProfileBacked) event.getEntity()).profileChanged()){
                ((ProfileBacked) event.getEntity()).persistProfile();
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
