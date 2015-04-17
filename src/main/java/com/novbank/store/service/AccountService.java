package com.novbank.store.service;

import com.novbank.store.domain.graph.Account;

/**
 * Created by HP on 2015/4/16.
 */
public interface AccountService {
    Account save(Account account);

    Account findByProfileId(String profileId);
}
