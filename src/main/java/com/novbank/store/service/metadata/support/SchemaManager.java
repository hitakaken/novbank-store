package com.novbank.store.service.metadata.support;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeResolver;
import com.google.common.reflect.TypeToken;
import com.mysema.query.types.Path;
import com.novbank.store.domain.base.resource.*;
import com.novbank.store.service.metadata.schema.GlobalProperty;
import com.novbank.store.service.metadata.schema.MetaClass;
import com.novbank.store.service.metadata.schema.PropertyType;
import com.novbank.store.service.metadata.schema.Schema;
import com.novbank.store.service.metadata.support.entity.ResourceGlobalProperty;
import com.novbank.store.service.metadata.support.entity.ResourceMetaClass;
import com.novbank.store.service.metadata.support.entity.ResourceMetaProperty;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.AnnotatedTypeScanner;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import static com.mysema.query.collections.CollQueryFactory.*;
import static com.mysema.query.alias.Alias.*;
import static com.mysema.query.support.Expressions.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by CaoKe on 2015/4/20.
 */
@Service
public class SchemaManager implements Schema<ResourceMetaClass>{
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
    public ResourceMetaClass createClass(Class<?> iClass) {
        return null;
    }

    @Override
    public ResourceMetaClass createClass(String iClassName) {
        return null;
    }

    @Override
    public ResourceMetaClass createClass(String iClassName, ResourceMetaClass iSuperClass) {
        return null;
    }

    @Override
    public ResourceMetaClass createAbstractClass(Class<?> iClass) {
        return null;
    }

    @Override
    public ResourceMetaClass createAbstractClass(String iClassName) {
        return null;
    }

    @Override
    public ResourceMetaClass createAbstractClass(String iClassName, ResourceMetaClass iSuperClass) {
        return null;
    }

    @Override
    public void dropClass(String iClassName) {

    }

    @Override
    public boolean existsClass(String iClassName) {
        return nativeClasses.containsKey(iClassName);
    }

    @Override
    public ResourceMetaClass getClass(Class<?> iClass) {
        return null;
    }

    @Override
    public ResourceMetaClass getClass(String iClassName) {
        return nativeClasses.get(iClassName);
    }

    @Override
    public ResourceMetaClass getOrCreateClass(String iClassName) {
        return nativeClasses.get(iClassName);
    }

    @Override
    public ResourceMetaClass getOrCreateClass(String iClassName, ResourceMetaClass iSuperClass) {
        return null;
    }

    @Override
    public Collection<ResourceMetaClass> getClasses() {
        return nativeClasses.values();
    }

    @Override
    public List<GlobalProperty> getGlobalProperties() {
        return null;
    }

    private final AnnotatedTypeScanner scanner = new AnnotatedTypeScanner(ResourceEntity.class);

    //扫描包
    public void scanPackage(){
        if(settings.getAutoScanPackages()==null)
            return;
        parse(scanner.findTypes(settings.getAutoScanPackages()));
    }

    //获取Cglib父类，该步可能引入Bug
    public Set<Class<?>> getUserClass(Set<Class<?>> classes){
        Set<Class<?>> results = Sets.newHashSet();
        for(Class<?> clazz : classes)
            results.add(ClassUtils.getUserClass(clazz));
        return results;
    }

    //解析类信息
    public Set<ResourceMetaClass> parse(Class<?>... classes){
        if(classes!=null)
            return parse(Sets.newHashSet(classes));
        return null;
    }
    public Set<ResourceMetaClass> parse(Set<Class<?>> classes){
        Map<String, ResourceMetaClass> cache = Maps.newHashMap();
        for(Class<?> clazz : getUserClass(classes)){
            traversal(clazz,cache);
        }
        for(String key:cache.keySet()){
            initialize(cache.get(key));
        }
        nativeClasses.putAll(cache);
        return Sets.newHashSet(cache.values());
    }

    private void initialize(ResourceMetaClass resourceMetaClass) {
        if(resourceMetaClass.isInitialized())
            return;
        if(resourceMetaClass.getSuperClass()!=null && !resourceMetaClass.getSuperClass().isInitialized())
            initialize(resourceMetaClass.getSuperClass());
        TypeToken type = TypeToken.of(resourceMetaClass.getJavaClass());
        Set<String> transients = Sets.newHashSet();
        Map<String,Field> fields = Maps.newHashMap();
        Map<String,Method> getters = Maps.newHashMap();
        Map<String,Method> setters = Maps.newHashMap();
        Map<String,ResourceProperty> annotations = Maps.newHashMap();
        Map<String,ResourceRelation> relations = Maps.newHashMap();
        Map<String,ResourceQuery> querys = Maps.newHashMap();
        Map<String,ResourceValidator> validators = Maps.newHashMap();
        for(Field field : resourceMetaClass.getJavaClass().getDeclaredFields()){
            if(Modifier.isTransient(field.getModifiers()) ||
                    field.isAnnotationPresent(javax.persistence.Transient.class)||
                    field.isAnnotationPresent(org.springframework.data.annotation.Transient.class))
            {transients.add(field.getName());continue;}
            fields.put(field.getName(),field);
            if(field.getAnnotation(ResourceProperty.class)!=null)
                annotations.put(field.getName(),field.getAnnotation(ResourceProperty.class));
            if(field.getAnnotation(ResourceRelation.class)!=null)
                relations.put(field.getName(),field.getAnnotation(ResourceRelation.class));
            if(field.getAnnotation(ResourceQuery.class)!=null)
                querys.put(field.getName(),field.getAnnotation(ResourceQuery.class));
            if(field.getAnnotation(ResourceValidator.class)!=null)
                validators.put(field.getName(),field.getAnnotation(ResourceValidator.class));
        }
        for(Method method : resourceMetaClass.getJavaClass().getDeclaredMethods()){
            String name = null; boolean isGetter = true;
            if(method.getName().startsWith("is") && method.getName().length()>2 && boolean.class.isAssignableFrom(method.getReturnType()) && method.getParameterTypes().length == 0)
                name = StringUtils.uncapitalize(method.getName().substring(2));
            else if(method.getName().startsWith("get") && method.getName().length()>3  && method.getParameterTypes().length == 0)
                name = StringUtils.uncapitalize(method.getName().substring(3));
            else if(method.getName().startsWith("set") && method.getName().length()>3  && method.getParameterTypes().length == 1)
            {   name = StringUtils.uncapitalize(method.getName().substring(3)); isGetter = false;   }
            if(name == null || transients.contains(name)) continue;
            if(Modifier.isTransient(method.getModifiers()) ||
                    method.isAnnotationPresent(javax.persistence.Transient.class)||
                    method.isAnnotationPresent(org.springframework.data.annotation.Transient.class)){
                transients.add(name);
                fields.remove(name);getters.remove(name);setters.remove(name);
                continue;
            }
            if(isGetter) getters.put(name,method); else setters.put(name,method);
            if(method.getAnnotation(ResourceProperty.class)!=null && !annotations.containsKey(name))
                annotations.put(name,method.getAnnotation(ResourceProperty.class));
            if(method.getAnnotation(ResourceRelation.class)!=null && !relations.containsKey(name))
                relations.put(name,method.getAnnotation(ResourceRelation.class));
            if(method.getAnnotation(ResourceQuery.class)!=null && !querys.containsKey(name))
                querys.put(name,method.getAnnotation(ResourceQuery.class));
            if(method.getAnnotation(ResourceValidator.class)!=null && !validators.containsKey(name))
                validators.put(name,method.getAnnotation(ResourceValidator.class));
        }
        //遍历 field/getter 并集，忽略无读取方法的setter
        List<ResourceMetaProperty> properties = Lists.newArrayList();
        for(String name : Sets.union(fields.keySet(),getters.keySet())){
            ResourceMetaProperty property = new ResourceMetaProperty();
            property.setName(name);
            if(fields.containsKey(name)) property.setField(fields.get(name));
            if(getters.containsKey(name)) property.setGetter(getters.get(name));
            if(setters.containsKey(name))
                property.setSetter(setters.get(name));
            else
                property.setReadOnly(true);
            property.setAlias(name);
            property.setNamespace(resourceMetaClass.getNamespace());
            property.setReadOnly(false);
            property.setProfileOnly(true);
            property.setNullable(true); //ReturnType ?
            property.setCached(false);
            if(querys.containsKey(name)){
                ResourceQuery annotation = querys.get(name);
                property.setAlias(StringUtils.isNotBlank(annotation.name()) ? annotation.name() : name);
                property.setNamespace(StringUtils.isNotBlank(annotation.namespace()) ? annotation.namespace() : resourceMetaClass.getNamespace());
                property.setReadOnly(true);
                property.setProfileOnly(annotation.profileOnly());
                property.setNullable(true);
                property.setCached(annotation.cached());
                property.setQueryStrategy(annotation.value());
                property.setType(PropertyType.DYNAMIC);
                property.setLinkedType(annotation.target());
            }else if(annotations.containsKey(name)){
                ResourceProperty annotation = annotations.get(name);
                property.setAlias(StringUtils.isNotBlank(annotation.value()) ? annotation.value() : name);
                property.setNamespace(StringUtils.isNotBlank(annotation.namespace()) ? annotation.namespace() : resourceMetaClass.getNamespace());
                property.setReadOnly(annotation.readOnly());
                property.setProfileOnly(annotation.profileOnly());
                property.setNullable(annotation.nullable());
                property.setCached(annotation.cached());
                property.setType(PropertyType.PROPERTY);
            }else if(relations.containsKey(name)){
                ResourceRelation annotation = relations.get(name);
                property.setAlias(StringUtils.isNotBlank(annotation.value())?annotation.value():name);
                property.setNamespace(StringUtils.isNotBlank(annotation.namespace())?annotation.namespace():resourceMetaClass.getNamespace());
                property.setReadOnly(annotation.readOnly());
                property.setProfileOnly(false);
                property.setNullable(annotation.nullable());
                property.setCached(annotation.cached());
                property.setType(PropertyType.RELATION);
                property.setLinkedType(annotation.target());
            }
            if(validators.containsKey(name))
                property.setValidatorStrategy(validators.get(name).value());
            TypeToken returnType = TypeToken.of(fields.containsKey(name)?fields.get(name).getType():getters.get(name).getReturnType());
            property.setReturnType(returnType.toString());
            property.setRawReturnType(returnType);
            properties.add(property);
        }
        resourceMetaClass.setDeclaredProperties(properties);
    }


    //遍历类信息
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
        String key = clazz.getCanonicalName();
        if(cache.containsKey(key)) return cache.get(key);
        if(nativeClasses.containsKey(key)) return nativeClasses.get(key);
        //TODO 实现overwrite机制
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
