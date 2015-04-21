package com.novbank.store.service.account;

import com.novbank.store.domain.graph.Account;

/**
 * Created by Cao Ke on 2015/4/16.
 */
public interface AccountService {
    Account save(Account account);
    Account findByName(String name);
    Account findByProfileId(String profileId);
}
