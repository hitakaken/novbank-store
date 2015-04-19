package com.novbank.store.domain.graph;

import com.novbank.store.domain.base.profile.ProfileEntity;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by HP on 2015/4/14.
 */
@NodeEntity
@ProfileEntity
public class Project extends Identifiable{

    public Project() {

    }


}
