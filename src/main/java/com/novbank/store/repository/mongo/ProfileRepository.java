package com.novbank.store.repository.mongo;

import com.novbank.store.domain.document.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by CaoKe on 2015/4/18.
 */
public interface ProfileRepository extends MongoRepository<Profile,String>,QueryDslPredicateExecutor<Profile> {

}
