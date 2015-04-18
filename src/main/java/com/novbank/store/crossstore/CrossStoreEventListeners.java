package com.novbank.store.crossstore;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.lifecycle.AfterSaveEvent;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import static com.novbank.store.crossstore.CrossStoreConstants.*;
/**
 * Created by CaoKe on 2015/4/17.
 */
public class CrossStoreEventListeners {
    public static class AfterSaveEventListener implements ApplicationListener<AfterSaveEvent>{
        private Neo4jTemplate neo4jTemplate;
        private MongoTemplate mongoTemplate;

        public AfterSaveEventListener(Neo4jTemplate neo4jTemplate, MongoTemplate mongoTemplate) {
            this.neo4jTemplate = neo4jTemplate;
            this.mongoTemplate = mongoTemplate;
        }

        @Override
        public void onApplicationEvent(AfterSaveEvent event) {
            System.out.println("Call me!");
            if((event.getEntity() instanceof ProfiledBacked) && ((ProfiledBacked) event.getEntity()).isProfileChanged()){
                ProfiledBacked profile = (ProfiledBacked) event.getEntity();
                mongoTemplate.save(profile.profile(),PROFILE_COLLECTION_NAME);
                profile.completeProfileChange();
                if(event.getEntity() instanceof NodeBacked){
                    /*Node node = ((NodeBacked) event.getEntity()).getPersistentState();
                    if(!node.hasProperty(PROFILE_ID_FIELD) || !node.getProperty(PROFILE_ID_FIELD).equals(profile.getProfileId())){
                        node.setProperty(PROFILE_ID_FIELD, profile.getProfileId());
                        //Index<Node> index=  neo4jTemplate.getIndex(PROFILE_ID_INDEX, null);
                        //index.add(node,PROFILE_ID_FIELD,profile.profileId());
                        //neo4jTemplate.index(PROFILE_ID_INDEX, node, PROFILE_ID_FIELD, profile.profileId());
                        neo4jTemplate.save(event.getEntity());
                    }*/
                }
            }
        }
    }
}
