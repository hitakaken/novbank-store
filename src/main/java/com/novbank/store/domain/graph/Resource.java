package com.novbank.store.domain.graph;


import com.novbank.store.domain.base.profile.ProfileEntity;
import com.novbank.store.domain.base.resource.ResourceUtils;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.util.Assert;
import org.springframework.util.SystemPropertyUtils;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CaoKe on 2015/4/15.
 */
@NodeEntity
@ProfileEntity
public class Resource extends Identifiable{
    //命名空间，采用缩写形式
    @Fetch @Indexed
    private String namespace;

    @Fetch @Indexed
    private String metaClass;

    @Fetch @Indexed
    private String uniqueKey;

    @Fetch @Indexed(unique = true)
    private String url;

    public Resource() {

    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
        reloadUrl();
    }

    public String getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(String metaClass) {
        this.metaClass = metaClass;
        reloadUrl();
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
        reloadUrl();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if(validateUrl(url))
            this.url = url;
    }

    private void reloadUrl() {
        if(namespace!=null && metaClass!=null && uniqueKey!=null)
            url = namespace+ ResourceUtils.SEPARATOR + metaClass + ResourceUtils.SEPARATOR + uniqueKey;
    }

    private boolean validateUrl(String url) {
        Matcher m = ResourceUtils.URL_PATTERN.matcher(url);
        if(m.matches()){
            namespace = m.group(1);
            metaClass = m.group(2);
            uniqueKey = m.group(3);
            return true;
        }
        return false;
    }


}
