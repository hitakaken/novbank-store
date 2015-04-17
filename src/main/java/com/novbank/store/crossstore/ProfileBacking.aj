package com.novbank.store.crossstore;

import com.novbank.store.domain.document.Profile;
import com.novbank.store.repository.mongo.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;

/**
 * Created by HP on 2015/4/17.
 */
public privileged aspect ProfileBacking {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileBacking.class);
    declare parents : (@Profiled *) implements ProfileBacked;

    private MongoTemplate mongoTemplate;
    private ProfileRepository profiles;
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public ProfileRepository getProfileRepository() {
        return profiles;
    }

    public void setProfileRepository(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    private transient Profile ProfileBacked._profile;

    public Profile ProfileBacked.profile(){
        if(_profile!=null)
            return _profile;
        if(this instanceof NodeBacked){
            Long nodeId = ((NodeBacked)this).getNodeId();
            if(nodeId!=null){
                _profile = profileRepository().findOneByGraphIdAndAsNode(nodeId,true);
                if(_profile == null)
                    _profile = new Profile(nodeId,true);
            }else
                _profile = new Profile(true);
        }else if(this instanceof RelationshipBacked){
            Long relationshipId = ((RelationshipBacked)this).getRelationshipId();
            if(relationshipId!=null){
                _profile = profileRepository().findOneByGraphIdAndAsNode(relationshipId,false);
                if(_profile == null)
                    _profile = new Profile(relationshipId,false);
            }else
                _profile = new Profile(false);
        }
        return _profile;
    }

    public Profile ProfileBacked._profile(){
        return _profile;
    }

    public static MongoTemplate mongoTemplate() {
        return ProfileBacking.aspectOf().mongoTemplate;
    }
    public static ProfileRepository profileRepository() {
        return ProfileBacking.aspectOf().profiles;
    }

}
