package com.novbank.store.domain.base.profile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.novbank.store.domain.document.GraphInfo;
import com.novbank.store.domain.document.Profile;
import com.novbank.store.domain.graph.Identifiable;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Relationship;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.neo4j.aspects.core.GraphBacked;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;
import org.springframework.data.neo4j.mapping.ManagedEntity;

import java.util.Map;
import java.util.Set;

/**
 * Mongo 存储 Profile
 *
 * Created by CaoKe on 2015/4/19.
 */
public class MongoProfileBacking extends AbstractDelegateProfile implements ProfileBacked {
    @Transient
    private transient MongoTemplate mongoOps;
    @Transient
    private transient Profile profile;
    @Transient
    private transient Object source;

    public MongoProfileBacking(Object source, MongoTemplate mongoTemplate) {
        this.source = source;
        this.mongoOps = mongoTemplate;
    }

    @Override
    public ProfileSupport delegate() {
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
        if(source instanceof ManagedEntity)
            ((ManagedEntity) source).persist();
    }

    @Override
    public void backupGraph() {
        GraphInfo graph  = new GraphInfo();
        if(source instanceof NodeBacked){
            graph.setGraphId(((NodeBacked) source).getNodeId());
            graph.setType("Node");
            Node node = ((NodeBacked) source).getPersistentState();
            Set<String> labels = Sets.newHashSet();
            for(Label label:node.getLabels()){
                labels.add(label.name());
            }
            graph.setLabels(labels);
            graph.setProperties(readProperties(node));
            Set<Map<String,Object>> relations = Sets.newHashSet();
            for(Relationship relation: node.getRelationships()){
                Map<String,Object> relationProp = readProperties(relation);
                //relationProp.put("_relationship_direction_type", relation.get())
                relation.getOtherNode(node);
            }
            graph.setRelations(relations);
        }
        if(source instanceof RelationshipBacked){
            graph.setGraphId(((RelationshipBacked) source).getRelationshipId());
            graph.setType("Relation");
            Relationship relation = ((RelationshipBacked) source).getPersistentState();
            graph.setLabels(Sets.newHashSet(relation.getType().name()));
            graph.setProperties(readProperties(relation));
        }
        if(source instanceof Identifiable){
            graph.setVersion(((Identifiable) source).getVersion());
        }
        graph.setBackupTime(DateTime.now(DateTimeZone.forID("GMT+8")));
    }

    private Map<String,Object> readProperties(PropertyContainer pc){
        Map<String,Object> properties = Maps.newHashMap();
        if(pc!=null)
            for (String key:pc.getPropertyKeys()){
                properties.put(key,pc.getProperty(key));
            }
        return properties;
    }
}
