package com.novbank.store.crossstore;


/**
 * Created by CaoKe on 2015/4/17.
 */
//@Configuration
public class CrossStoreConstants {
    public final static String PROFILE_ID_FIELD = "_profile_id";
    public final static String PROFILE_ID_INDEX = "_profile_id_index";
    public final static String PROFILE_COLLECTION_NAME = "profile";
    public static final String ID_FIELD = "_id";
    public static final String TYPE_FIELD = "_type";

    /*@Bean
    public NodeProfiledBacking nodeProfiledBacking(MongoTemplate mongoTemplate,ProfileRepository profiles) throws Exception {
        NodeProfiledBacking aspect = NodeProfiledBacking.aspectOf();
        aspect.setMongoTemplate(mongoTemplate);
        aspect.setProfileRepository(profiles);
        return aspect;
    }

    @Bean
    public AfterSaveEventListener afterSaveEventListener(Neo4jTemplate neo4jTemplate, MongoTemplate mongoTemplate){
        return new AfterSaveEventListener(neo4jTemplate,mongoTemplate);
    }*/

}
