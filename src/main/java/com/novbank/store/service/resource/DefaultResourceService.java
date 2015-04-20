package com.novbank.store.service.resource;

import com.novbank.store.domain.graph.Resource;
import com.novbank.store.repository.mongo.ProfileRepository;
import com.novbank.store.repository.neo4j.ResourceRepository;
import com.novbank.store.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HP on 2015/4/17.
 */
@Service
@Transactional
public class DefaultResourceService implements ResourceService {
    @Autowired
    ResourceRepository resources;

    @Autowired
    ProfileRepository profiles;

    @Autowired(required = false)
    CacheManager cacheManager;

    @Override
    public <R extends Resource> R getOrCreate(String url) {
        return null;
    }

    @Override
    public <S> S getOrCreate(Class<S> rClass, String unique) {
        return null;
    }

    @Override
    public <R extends Resource> R getByUrl(String url) {
        return null;
    }

    @Override
    public <R extends Resource> R getByProfileId(String profileId) {
        return null;
    }

    @Override
    //@Cacheable(value = "resource", key = "#cacheKey")
    public <R extends Resource> R findOne(String query, String cacheKey) {
        return null;
    }

    @Override
    //@Cacheable(value = "resources", key = "#cacheKey")
    public <S> S findOne(String query, Class<S> rClass, String cacheKey) {
        return null;
    }

    @Override
    public <R extends Resource> Iterable<R> find(String query, String cacheKey) {
        return null;
    }

    @Override
    public <S> Iterable<S> find(String query, Class<S> rClass, String cacheKey) {
        return null;
    }

    @Override
    public <S extends Resource> S save(S resource) {
        return resources.save(resource);
    }

    @Override
    //@Cacheable(value = "resource", key = "#resource.url")
    public <S> S save(S resource) {
        //Step1 保存图和文档信息

        //Step2 更新索引

        //Step3 重建缓存

        return null;
    }

    public <S> Iterable<S> save(Iterable<S> resource) {
        return null;
    }


}
