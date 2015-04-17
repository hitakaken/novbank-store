package com.novbank.store.repository.neo4j;

import com.novbank.store.domain.graph.Account;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by HP on 2015/4/15.
 */
public interface AccountRepository extends GraphRepository<Account>,CypherDslRepository<Account>,ProfiledRepository<Account>{

}
