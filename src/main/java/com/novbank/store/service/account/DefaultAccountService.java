package com.novbank.store.service.account;

import com.novbank.store.domain.base.profile.ProfileBacked;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.repository.neo4j.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Cao Ke on 2015/4/17.
 */
@Service
@Transactional
public class DefaultAccountService implements AccountService {
    @Autowired
    AccountRepository accounts;

    @Override
    public Account save(Account account) {
        return accounts.save(account);
    }

    @Override
    public Account findByName(String name) {
        return accounts.findByName(name);
    }

    @Override
    public Account findByProfileId(String profileId) {
        return accounts.findByPropertyValue(ProfileBacked.PROFILE_ID_FIELD, profileId);
    }
}
