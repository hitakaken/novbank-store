package com.novbank.store.repository.mongo;

import com.novbank.store.domain.document.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by HP on 2015/4/17.
 */
public interface ProfileRepository extends MongoRepository<Profile,String> /*,QueryDslPredicateExecutor<Profile>*/ {
    Profile findOneByGraphIdAndAsNode(long graphId, boolean asNode);
}
