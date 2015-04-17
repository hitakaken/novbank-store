package com.novbank.store.crossstore;

import com.novbank.store.repository.mongo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by HP on 2015/4/17.
 */
@Component
public class DoSaveProfileListener implements ApplicationListener<AfterSaveEvent> {
    @Autowired
    private ProfileRepository profiles;

    @Override
    public void onApplicationEvent(AfterSaveEvent event) {
        Object entity = event.getEntity();
        if((entity instanceof ProfileBacked) && ((ProfileBacked) entity).profileOrigin() !=null){
            profiles.save(((ProfileBacked) entity).profile());
        }
    }
}
