package com.novbank.store.domain.graph;


import com.novbank.store.domain.base.profile.ProfileEntity;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by CaoKe on 2015/4/15.
 */
@NodeEntity
@ProfileEntity
public class Resource extends Identifiable{
    /**
     *
     */
    @Indexed(unique = true)
    private String url;

    public Resource() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
