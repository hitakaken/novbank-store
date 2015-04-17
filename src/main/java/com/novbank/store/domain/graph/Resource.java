package com.novbank.store.domain.graph;


import com.novbank.store.crossstore.Profiled;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by CaoKe on 2015/4/15.
 */
@NodeEntity
@Profiled
public class Resource extends Identifiable {

    public Resource() {

    }

}
