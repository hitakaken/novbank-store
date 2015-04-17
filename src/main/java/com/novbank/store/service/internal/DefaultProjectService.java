package com.novbank.store.service.internal;

import com.novbank.store.repository.neo4j.ProjectRepository;
import com.novbank.store.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by HP on 2015/4/17.
 */
@Component
public class DefaultProjectService implements ProjectService{
    @Autowired
    ProjectRepository projects;

}
