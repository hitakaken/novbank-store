package com.novbank.store.crossstore;

import com.novbank.store.domain.document.Profile;

import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 2015/4/17.
 */
public interface ProfileBacked {
    Profile profile();
    Profile profileOrigin();
    Object profilePut(String key, Object v);
    void profilePutAll(Profile other);
    void profilePutAll(Map m);
    Object profileGet(String key);
    Object profileRemoveField(String key);
    boolean profileContainsField(String s);
    Set<String> profileKeySet();
}
