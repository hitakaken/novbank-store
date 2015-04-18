package com.novbank.store.domain.graph;


import com.novbank.store.crossstore.ProfiledNode;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Set;

/**
 * Created by CaoKe on 2015/4/15.
 */
@NodeEntity
@ProfiledNode
public class Resource extends Identifiable{

    public Resource() {

    }


}
