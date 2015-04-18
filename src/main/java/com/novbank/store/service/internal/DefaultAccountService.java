package com.novbank.store.service.internal;

import com.novbank.store.crossstore.CrossStoreConstants;
import com.novbank.store.domain.graph.Account;
import com.novbank.store.repository.neo4j.AccountRepository;
import com.novbank.store.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HP on 2015/4/17.
 */
@Component
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
        return accounts.findByPropertyValue(CrossStoreConstants.PROFILE_ID_FIELD, profileId);
    }
}
