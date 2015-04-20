package com.novbank.store.domain.base.profile;


/**
 * Created by HP on 2015/4/17.
 */
public interface ProfileBacked extends ProfileSupport {
    String PROFILE_COLLECTION_NAME = "profile";
    String PROFILE_ID_FIELD = "_profile_id";
    String PROFILE_ID_INDEX = "_profile_id_index";
    String ID_FIELD = "_id";
    String TYPE_FIELD = "_type";
    String DIRECTION_FIELD= "_direction";

    boolean profileInitialized();
    boolean profileLoaded();
    boolean profileChanged();
    String profileId();
    void initializeProfile();
    void persistProfile();
    void backupGraph();
    void save();
}
