package com.novbank.store.crossstore;


import com.novbank.store.domain.document.Profile;
import com.novbank.store.domain.Profiled;

/**
 * Created by HP on 2015/4/17.
 */
public interface ProfiledBacked extends Profiled {
    String getProfileId();
    //Profile profile();
    boolean isProfileChanged();
    void completeProfileChange();
}
