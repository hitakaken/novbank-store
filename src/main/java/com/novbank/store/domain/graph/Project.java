package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.ProfiledEntity;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by HP on 2015/4/14.
 */
@NodeEntity
@ProfiledEntity
public class Project extends Identifiable{

    public Project() {

    }


}
