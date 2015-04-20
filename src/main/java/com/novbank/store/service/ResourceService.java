package com.novbank.store.service;

import com.novbank.store.domain.base.resource.ResourceBacked;
import com.novbank.store.domain.graph.Resource;

/**
 * Created by HP on 2015/4/17.
 */
public interface ResourceService {
    /**
     *
     * @param url
     * @param <R>
     * @return
     */
    <R extends Resource> R getOrCreate(String url);
    <S extends ResourceBacked> S getOrCreate(Class<S> rClass, String unique);

    <R extends Resource> R getByUrl(String url);
    <R extends Resource> R getByProfileId(String profileId);

    /**
     *
     * @param query
     * @param cacheKey
     * @param <R>
     * @return
     */
    <R extends Resource> R findOne(String query, String cacheKey);
    <S extends ResourceBacked> S findOne(String query,Class<S> rClass, String cacheKey);

    /**
     *
     * @param query
     * @param cacheKey
     * @param <R>
     * @return
     */
    <R extends Resource> Iterable<R> find(String query, String cacheKey);
    <S extends ResourceBacked> Iterable<S> find(String query, Class<S> rClass, String cacheKey);

    /**
     *
     * @param resource
     * @param <R>
     * @return
     */
    <R extends Resource> R save(R resource);
    <S extends ResourceBacked> S save(S resource);


}
