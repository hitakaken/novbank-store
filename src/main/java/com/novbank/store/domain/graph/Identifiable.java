package com.novbank.store.domain.graph;

import com.novbank.store.domain.base.profile.ProfileBacked;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.aspects.core.NodeBacked;
import org.springframework.data.neo4j.aspects.core.RelationshipBacked;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by Cao Ke on 2015/4/15.
 */
public abstract class Identifiable implements Serializable{
    @GraphId
    @Id
    protected Long id;

    @CreatedDate
    protected DateTime createTime;

    @LastModifiedDate
    protected DateTime lastModified;

    @Version
    protected Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Transient
    public boolean isNode(){
        return (this instanceof NodeBacked);
    }

    public NodeBacked asNode(){
        if(isNode())
            return (NodeBacked) this;
        return null;
    }

    @Transient
    public boolean isRelationship(){
        return (this instanceof RelationshipBacked);
    }

    public RelationshipBacked asRelationship(){
        if(isRelationship())
            return (RelationshipBacked) this;
        return null;
    }

    public boolean hasProfile(){
        return (this instanceof ProfileBacked);
    }

    public ProfileBacked asProfiled(){
        if(hasProfile())
            return (ProfileBacked) this;
        return null;
    }

}
