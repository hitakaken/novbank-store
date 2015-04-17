package com.novbank.store.service;

import com.novbank.store.domain.graph.Account;
import com.novbank.store.repository.neo4j.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by HP on 2015/4/17.
 */
@Component
public class DefaultAccountService implements AccountService{
    @Autowired
    AccountRepository accounts;

    @Override
    public Account save(Account account) {
        return accounts.save(account);
    }
}
