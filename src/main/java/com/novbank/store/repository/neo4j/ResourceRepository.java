package com.novbank.store.repository.neo4j;

import com.novbank.store.domain.graph.Resource;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by CaoKe on 2015/4/15.
 */
public interface ResourceRepository extends GraphRepository<Resource>,CypherDslRepository<Resource> {

}
