package com.novbank.store.repository.neo4j;


import com.novbank.store.domain.graph.Project;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by HP on 2015/4/15.
 */
public interface ProjectRepository extends GraphRepository<Project>,CypherDslRepository<Project> {
}
