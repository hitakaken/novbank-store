package com.novbank.store.domain.base.resource;

import com.novbank.store.domain.document.Profile;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

/**
 * Created by Cao Ke on 2015/4/20.
 */
public class Relation implements Relationship{
    private Relationship relationship;
    private Profile profile;

    public Relation() {

    }


    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void delete() {

    }

    @Override
    public Node getStartNode() {
        return null;
    }

    @Override
    public Node getEndNode() {
        return null;
    }

    @Override
    public Node getOtherNode(Node node) {
        return null;
    }

    @Override
    public Node[] getNodes() {
        return new Node[0];
    }

    @Override
    public RelationshipType getType() {
        return null;
    }

    @Override
    public boolean isType(RelationshipType type) {
        return false;
    }

    @Override
    public GraphDatabaseService getGraphDatabase() {
        return null;
    }

    @Override
    public boolean hasProperty(String key) {
        return false;
    }

    @Override
    public Object getProperty(String key) {
        return null;
    }

    @Override
    public Object getProperty(String key, Object defaultValue) {
        return null;
    }

    @Override
    public void setProperty(String key, Object value) {

    }

    @Override
    public Object removeProperty(String key) {
        return null;
    }

    @Override
    public Iterable<String> getPropertyKeys() {
        return null;
    }
}
