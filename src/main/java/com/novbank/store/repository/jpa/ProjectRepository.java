package com.novbank.store.repository.jpa;


import com.novbank.store.domain.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by HP on 2015/4/15.
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project,String> {
}
