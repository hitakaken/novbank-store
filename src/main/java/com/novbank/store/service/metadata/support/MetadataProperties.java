package com.novbank.store.service.metadata.support;

import com.google.common.collect.Sets;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * Created by HP on 2015/4/21.
 */
@ConfigurationProperties(prefix = "novbank.metadata")
public class MetadataProperties {
    private String storeType;
    private String storePath;
    private boolean singleSchemaFile = false;
    private boolean singleFunctionFile = false;
    private boolean autoRegister = true;
    private boolean autoPersist = true;
    private Set<String> autoScanPackages = Sets.newHashSet();
    private boolean overwrite = false;

    public MetadataProperties() {
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public boolean isSingleSchemaFile() {
        return singleSchemaFile;
    }

    public void setSingleSchemaFile(boolean singleSchemaFile) {
        this.singleSchemaFile = singleSchemaFile;
    }

    public boolean isSingleFunctionFile() {
        return singleFunctionFile;
    }

    public void setSingleFunctionFile(boolean singleFunctionFile) {
        this.singleFunctionFile = singleFunctionFile;
    }

    public boolean isAutoRegister() {
        return autoRegister;
    }

    public void setAutoRegister(boolean autoRegister) {
        this.autoRegister = autoRegister;
    }

    public Set<String> getAutoScanPackages() {
        return autoScanPackages;
    }

    public void setAutoScanPackages(Set<String> autoScanPackages) {
        this.autoScanPackages = autoScanPackages;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
