package com.novbank.store.service.project;

import com.novbank.store.repository.neo4j.ProjectRepository;
import com.novbank.store.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HP on 2015/4/17.
 */
@Service
@Transactional
public class DefaultProjectService implements ProjectService{
    @Autowired
    ProjectRepository projects;

}
