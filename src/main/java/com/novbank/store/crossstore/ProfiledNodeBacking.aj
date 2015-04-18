package com.novbank.store.crossstore;

import com.mongodb.BasicDBObject;
import com.novbank.store.domain.document.Profile;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.aspects.core.NodeBacked;

import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 2015/4/17.
 */
public privileged aspect ProfiledNodeBacking {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfiledNodeBacking.class);
    declare parents : (@ProfiledNode *) implements ProfiledBacked;

    private MongoTemplate mongoTemplate;
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @GraphProperty
    @Indexed(unique = true)
    @Fetch
    private String ProfiledBacked.profileId;
    private transient Profile ProfiledBacked._profile;
    private transient boolean ProfiledBacked._changed = false;

    public NodeBacked ProfiledBacked.asNodeBack(){
        return (NodeBacked) this;
    }

    public boolean ProfiledBacked.hasProfileId(){
        return profileId!=null   || _profile!=null;
    }

    public String ProfiledBacked.getProfileId(){
        if (_profile!=null)
            return  _profile.getId();
        return profileId;
    }

    public Profile ProfiledBacked.profile(){
        if(_profile==null){
            if(profileId!=null){
                _profile = mongoTemplate().findById(profileId,Profile.class);
            }
            if(_profile == null)
                createProfile();
        }
        return _profile;
    }

    public void ProfiledBacked.createProfile(){
        _changed = true;
        profileId = ObjectId.get().toString();
        _profile = new Profile(profileId);
        /*if(asNodeBack().hasPersistentState())
            asNodeBack().getPersistentState().setProperty(CrossStoreConstants.PROFILE_ID_FIELD,profileId);*/
    }

    /*public Object ProfiledBacked.put(String key, Object v){
        _changed = true;
        return underlyingProfile().put(key,v);
    }

    public void ProfiledBacked.putAll(ProfiledBacked other){
        _changed = true;
        underlyingProfile().putAll((Profile) other.underlyingProfile());
    }
    public void ProfiledBacked.putAll(Map m){
        _changed = true;
        underlyingProfile().putAll(m);
    }
    public Object ProfiledBacked.get(String key){
        return underlyingProfile().get(key);
    }
    public Object ProfiledBacked.removeField(String key){
        _changed = true;
        return underlyingProfile().removeField(key);
    }
    public boolean ProfiledBacked.containsField(String s){
        return underlyingProfile().containsField(s);
    }
    public Set<String> ProfiledBacked.fieldSet(){
        return underlyingProfile().keySet();
    }*/

    public boolean ProfiledBacked.isProfileChanged(){
        return _changed;
    }

    public void ProfiledBacked.completeProfileChange(){
        _changed=false;
    }

    public static MongoTemplate mongoTemplate() {
        return ProfiledNodeBacking.aspectOf().mongoTemplate;
    }

}
