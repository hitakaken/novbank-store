package com.novbank.store.repository;

import com.novbank.store.domain.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by CaoKe on 2015/4/15.
 */
public interface ResourceRepository extends PagingAndSortingRepository<Resource,String> {

}
