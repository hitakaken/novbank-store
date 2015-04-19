package com.novbank.store.crossstore;

import com.novbank.store.domain.base.AbstractDelegateProfiled;
import com.novbank.store.domain.base.Profiled;
import com.novbank.store.domain.document.Profile;
import com.novbank.store.repository.mongo.ProfileRepository;
import org.bson.types.ObjectId;
import org.neo4j.graphdb.PropertyContainer;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.aspects.core.GraphBacked;
import org.springframework.data.neo4j.support.Neo4jTemplate;

/**
 * Created by CaoKe on 2015/4/19.
 */
public class DefaultProfiledEntityProxy extends AbstractDelegateProfiled implements ProfiledBacked {
    @Transient
    private transient Neo4jTemplate neo4jOps;
    @Transient
    private transient MongoTemplate mongoOps;
    @Transient
    private transient Profile profile;
    @Transient
    private transient Object source;

    public DefaultProfiledEntityProxy(Object source, Neo4jTemplate neo4jTemplate, MongoTemplate mongoTemplate) {
        this.source = source;
        this.neo4jOps = neo4jTemplate;
        this.mongoOps = mongoTemplate;
    }

    @Override
    public Profiled delegate() {
        return profile();
    }

    //缓存取自源对象的ProfileId，不要直接读取
    private transient String _sourceProfileId = null;

    public String sourceProfileId(){
        if(_sourceProfileId!=null)
            return _sourceProfileId;
        if((source instanceof GraphBacked) && ((GraphBacked) source).hasPersistentState()){
            PropertyContainer pc = (PropertyContainer) ((GraphBacked) source).getPersistentState();
            if(pc.hasProperty(PROFILE_ID_FIELD) && pc.getProperty(PROFILE_ID_FIELD)!=null)
                this._sourceProfileId =  pc.getProperty(PROFILE_ID_FIELD).toString();
        }
        return _sourceProfileId;
    }

    @Override
    public boolean profileInitialized() {
        return sourceProfileId()!=null;
    }

    @Override
    public boolean profileLoaded() {
        return profile!=null;
    }

    @Override
    public boolean profileChanged() {
        return profileLoaded() && profile.isChanged();
    }

    @Override
    public void initializeProfile() {
        if(!profileInitialized() && profileLoaded() && (source instanceof GraphBacked) && ((GraphBacked) source).hasPersistentState()){
            PropertyContainer pc = (PropertyContainer) ((GraphBacked) source).getPersistentState();
            pc.setProperty(PROFILE_ID_FIELD, profile.getId());
        }
    }

    @Override
    public String profileId() {
        if(sourceProfileId()!=null)
            return sourceProfileId();
        if (profile!=null)
            return profile.getId();
        return null;
    }

    public Profile profile() {
        if(profile==null)   loadProfile();
        return profile;
    }

    public void loadProfile(){
        if(profileInitialized() && profile==null){
            profile = mongoOps.findById(sourceProfileId(),Profile.class);
        }
        if(profile == null)  createProfile();
    }

    public void createProfile(){
        if(profile !=null) return;
        profile = new Profile(ObjectId.get().toString());
        profile.setChanged(true);
        initializeProfile();
    }

    public void persistProfile(){
        if(profileChanged())  {
            mongoOps.save(profile);
            profile.setChanged(false);
        }
        if(profileInitialized()) return;
        initializeProfile();
        neo4jOps.save(source);
    }
}
