package com.novbank.store.helper;

import com.mongodb.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.neo4j.rest.SpringCypherRestGraphDatabase;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CaoKe on 2015/4/19.
 */
public class DataSourceHelper {
    @ConfigurationProperties(prefix = "spring.data.neo4j")
    public static class  Neo4jProperties{
        private String url;
        private String username;
        private String password;

        public Neo4jProperties() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Neo4j 自动实现内嵌/远程模式
     *
     * @param properties
     * @return
     */
    public static GraphDatabaseService graphDatabaseService(Neo4jProperties properties){
        if(properties.getUrl().startsWith("local:")){
            return new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(properties.getUrl().substring(6)).newGraphDatabase();
        } else if(properties.getUrl().startsWith("remote:")){
            String url =properties.getUrl().substring(7);
            if(properties.getUsername()!=null && properties.getPassword()!=null){
                return new SpringCypherRestGraphDatabase(url,properties.getUsername(),properties.getPassword());
            }else
                return new SpringCypherRestGraphDatabase(url);
        }
        return null;
    }

    /**
     * 修正 Spring boot 对 Mongo3 的验证初始化支持
     *
     * @param properties
     * @param options
     * @return
     */
    public static Mongo mongo3(MongoProperties properties, MongoClientOptions options){
        try {
            if (hasCustomAddress(properties) || hasCustomCredentials(properties)) {
                if (options == null) {
                    options = MongoClientOptions.builder().build();
                }
                List<MongoCredential> credentials = null;
                if (hasCustomCredentials(properties)) {
                    String database = properties.getAuthenticationDatabase() == null ? properties.getMongoClientDatabase()
                            : properties.getAuthenticationDatabase();
                    //修正：Spring Boot 使用 Mongo-CR 验证，而Mongo3默认为 SCRAM-SHA-1
                    credentials = Arrays.asList(MongoCredential.createCredential(
                            properties.getUsername(), database, properties.getPassword()));
                }
                String host = properties.getHost() == null ? "localhost" : properties.getHost();
                int port = properties.getPort() == null ? 27017 : properties.getPort();
                return new MongoClient(Arrays.asList(new ServerAddress(host, port)),
                        credentials, options);
            }
            // The options and credentials are in the URI
            return new MongoClient(new MongoClientURI(properties.getUri(), builder(options)));
        }
        finally {
            properties.clearPassword();
        }
    }

    private static boolean hasCustomAddress(MongoProperties properties){
        return properties.getHost()!=null || properties.getPort()!=null;
    }

    private static boolean hasCustomCredentials(MongoProperties properties){
        return properties.getUsername()!=null && properties.getPassword()!=null;
    }

    private static MongoClientOptions.Builder builder(MongoClientOptions options) {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        if (options != null) {
            builder.alwaysUseMBeans(options.isAlwaysUseMBeans());
            builder.connectionsPerHost(options.getConnectionsPerHost());
            builder.connectTimeout(options.getConnectTimeout());
            builder.cursorFinalizerEnabled(options.isCursorFinalizerEnabled());
            builder.dbDecoderFactory(options.getDbDecoderFactory());
            builder.dbEncoderFactory(options.getDbEncoderFactory());
            builder.description(options.getDescription());
            builder.maxWaitTime(options.getMaxWaitTime());
            builder.readPreference(options.getReadPreference());
            builder.socketFactory(options.getSocketFactory());
            builder.socketKeepAlive(options.isSocketKeepAlive());
            builder.socketTimeout(options.getSocketTimeout());
            builder.threadsAllowedToBlockForConnectionMultiplier(options
                    .getThreadsAllowedToBlockForConnectionMultiplier());
            builder.writeConcern(options.getWriteConcern());
        }
        return builder;
    }
}
