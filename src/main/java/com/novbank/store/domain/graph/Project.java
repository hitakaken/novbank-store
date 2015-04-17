package com.novbank.store.domain.graph;

import com.novbank.store.crossstore.Profiled;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by HP on 2015/4/14.
 */
@NodeEntity
@Profiled
public class Project extends Identifiable{

    public Project() {

    }


}
