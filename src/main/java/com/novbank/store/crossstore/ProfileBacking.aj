package com.novbank.store.crossstore;

import com.novbank.store.domain.document.Profile;
import com.novbank.store.repository.mongo.ProfileRepository;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;
import org.springframework.util.ReflectionUtils;

import java.util.Map;
import java.util.Set;

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

    private transient Profile ProfileBacked._Profile;

    @GraphProperty
    @Indexed
    private String ProfileBacked._ProfileId;

    public Profile ProfileBacked.profile(){
        if(_Profile!=null)
            return _Profile;
        if(_ProfileId !=null) {
            _Profile = profileRepository().findOne(_ProfileId);
        }else{
            this._ProfileId = ObjectId.get().toString();
            _ProfileId = ObjectId.get().toString();
            _Profile = new Profile(_ProfileId);
        }
        return _Profile;
    }

    public Profile ProfileBacked.profileOrigin(){
        return _Profile;
    }

    public Object ProfileBacked.profilePut(String key, Object v){
        return profile().put(key,v);
    }
    public void ProfileBacked.profilePutAll(Profile other){
        profile().putAll((BSONObject) other.getFields());
    }
    public void ProfileBacked.profilePutAll(Map m){
        profile().putAll(m);
    }
    public Object ProfileBacked.profileGet(String key){
        return profile().get(key);
    }
    public Object ProfileBacked.profileRemoveField(String key){
        return profile().removeField(key);
    }
    public boolean ProfileBacked.profileContainsField(String s){
        return profile().containsField(s);
    }
    public Set<String> ProfileBacked.profileKeySet(){
        return profile().keySet();
    }

    public static MongoTemplate mongoTemplate() {
        return ProfileBacking.aspectOf().mongoTemplate;
    }
    public static ProfileRepository profileRepository() {
        return ProfileBacking.aspectOf().profiles;
    }

}
