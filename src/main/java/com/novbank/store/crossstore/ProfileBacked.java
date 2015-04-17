package com.novbank.store.crossstore;

import com.novbank.store.domain.document.Profile;

/**
 * Created by HP on 2015/4/17.
 */
public interface ProfileBacked {
    Profile profile();
    Profile _profile();
}
