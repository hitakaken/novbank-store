package com.novbank.store.domain.document;

import org.joda.time.DateTime;

import java.util.Map;
import java.util.Set;

/**
 * Created by HP on 2015/4/20.
 */
public class GraphInfo {
    protected Long graphId;
    protected String type; //Node or Relationship
    protected Long version; //Check to update or not
    protected DateTime backupTime;
    protected Set<String> labels;
    protected Map<String,Object> properties;
    protected Set<Map<String,Object>> relations;

    public GraphInfo() {
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public DateTime getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(DateTime backupTime) {
        this.backupTime = backupTime;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Set<Map<String, Object>> getRelations() {
        return relations;
    }

    public void setRelations(Set<Map<String, Object>> relations) {
        this.relations = relations;
    }
}
