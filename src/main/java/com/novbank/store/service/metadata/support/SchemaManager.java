package com.novbank.store.service.metadata.support;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.novbank.store.domain.base.resource.Namespace;
import com.novbank.store.domain.base.resource.ResourceEntity;
import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.repository.SchemaRepository;
import com.novbank.store.service.metadata.support.entity.ResourceGlobalProperty;
import com.novbank.store.service.metadata.support.entity.ResourceMetaClass;
import com.novbank.store.service.metadata.support.repository.DefaultXmlRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.AnnotatedTypeScanner;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Service
public class SchemaManager implements Schema{
    public final static int CURRENT_VERSION  = 1;

    @Autowired
    private MetadataProperties settings;

    private final Map<String, ResourceMetaClass> nativeClasses = new HashMap<>();
    private final Map<String, ResourceMetaClass> proxyClasses = new HashMap<>();
    private final Map<String, ResourceMetaClass> dynamicClasses = new HashMap<>();
    private final List<ResourceGlobalProperty> properties = new ArrayList<>();


    private File schemaFile;

    @Override
    public void create() {
        if(settings.isSingleSchemaFile()){
            if(settings.getStoreType().equalsIgnoreCase("xml"))
                schemaFile = new File(settings.getStorePath(),"schema.xml");
            else if(settings.getStoreType().equalsIgnoreCase("json"))
                schemaFile = new File(settings.getStorePath(),"schema.json");
            else if(settings.getStoreType().equalsIgnoreCase("sqlite3"))
                schemaFile = new File(settings.getStorePath(),"schema.db");
            if(!schemaFile.getParentFile().exists())
                schemaFile.getParentFile().mkdirs();
        }else{
            schemaFile = new File(settings.getStorePath(),"schema");
            if(!schemaFile.exists()) schemaFile.mkdirs();
        }
        reload();
    }



    @Override
    public void reload() {
        nativeClasses.clear();
        proxyClasses.clear();
        dynamicClasses.clear();
        properties.clear();
        //TODO: 从 XML/JSON/Database 中读取 Schema
        scanPackage();
    }

    @Override
    public void persist() {
        //TODO: 将 Schema 保存到 XML/JSON/Database
    }

    @Override
    public void close() {
        nativeClasses.clear();
        proxyClasses.clear();
        dynamicClasses.clear();
        properties.clear();
    }

    @Override
    public int countClasses() {
        return nativeClasses.size() + proxyClasses.size() + dynamicClasses.size();
    }

    @Override
    public MetaClass createClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass createClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass createClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass createAbstractClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public void dropClass(String iClassName) {

    }

    @Override
    public boolean existsClass(String iClassName) {
        return false;
    }

    @Override
    public MetaClass getClass(Class<?> iClass) {
        return null;
    }

    @Override
    public MetaClass getClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass getOrCreateClass(String iClassName) {
        return null;
    }

    @Override
    public MetaClass getOrCreateClass(String iClassName, MetaClass iSuperClass) {
        return null;
    }

    @Override
    public Collection<MetaClass> getClasses() {
        return null;
    }

    @Override
    public List<GlobalProperty> getGlobalProperties() {
        return null;
    }

    private final AnnotatedTypeScanner scanner = new AnnotatedTypeScanner(ResourceEntity.class);

    public void scanPackage(){
        if(settings.getAutoScanPackages()==null)
            return;
        parse(scanner.findTypes(settings.getAutoScanPackages()));
    }

    public Set<Class<?>> getUserClass(Set<Class<?>> classes){
        Set<Class<?>> results = Sets.newHashSet();
        for(Class<?> clazz : classes)
            results.add(ClassUtils.getUserClass(clazz));
        return results;
    }

    public void parse(Class<?>... classes){
        if(classes!=null)
            parse(Sets.newHashSet(classes));
    }

    public void parse(Set<Class<?>> classes){
        Map<String,ResourceMetaClass> cache = Maps.newHashMap();
        for(Class<?> clazz : getUserClass(classes)){
            traversal(clazz,cache);
        }
        System.out.println(cache);
    }

    public ResourceMetaClass traversal(Class<?> clazz,Map<String,ResourceMetaClass> cache){
        ResourceMetaClass superClass = null;
        if(clazz.getSuperclass()!=null && clazz.getSuperclass().getAnnotation(ResourceEntity.class)!=null){
            superClass = traversal(clazz.getSuperclass(), cache);
        }
        ResourceEntity annotation = clazz.getAnnotation(ResourceEntity.class);
        String name = StringUtils.isNotBlank(annotation.name())?annotation.name():ClassUtils.getShortName(clazz);
        String namespace;
        if(StringUtils.isNotBlank(annotation.namespace()))
            namespace = annotation.namespace();
        else if(clazz.getAnnotation(Namespace.class)!=null && StringUtils.isNotBlank(clazz.getAnnotation(Namespace.class).value()))
            namespace = clazz.getAnnotation(Namespace.class).value();
        else if(clazz.getPackage().getAnnotation(Namespace.class)!=null && StringUtils.isNotBlank(clazz.getPackage().getAnnotation(Namespace.class).value()))
            namespace = clazz.getPackage().getAnnotation(Namespace.class).value();
        else
            namespace = ClassUtils.getShortName(clazz.getPackage().getName());
        String key = namespace + ":" +name;
        if(cache.containsKey(key)) return cache.get(key);
        if(nativeClasses.containsKey(key)) return nativeClasses.get(key);
        ResourceMetaClass metaClass = new ResourceMetaClass();
        metaClass.setName(name);
        metaClass.setNamespace(namespace);
        metaClass.setIsAbstract(Modifier.isAbstract(clazz.getModifiers()));
        if(superClass!=null){
            metaClass.setSuperClass(superClass);
            metaClass.setSuperClassName(superClass.getNamespace() + ":" + superClass.getName());
        }
        metaClass.setJavaClassName(clazz.getCanonicalName());
        metaClass.setJavaClass(clazz);
        if(annotation.cacheable()){
            metaClass.setCacheName(StringUtils.isNotBlank(annotation.cacheName())?annotation.cacheName():name);
        }
        cache.put(key,metaClass);
        return metaClass;
    }
}
