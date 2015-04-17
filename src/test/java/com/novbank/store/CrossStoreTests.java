package com.novbank.store;

import com.novbank.store.domain.Account;
import com.novbank.store.repository.jpa.AccountRepository;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by HP on 2015/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
@WebIntegrationTest
public class CrossStoreTests {
    @Autowired
    EntityManager em;

    @Autowired
    AccountRepository accounts;

    @Autowired
    MongoMappingContext mappingContext;

    @Autowired(required = false)
    private EntityManagerFactory entityManagerFactory;

    @Autowired(required = false)
    private HibernateEntityManagerFactory hibernateEntityManagerFactory;

    @Test
    public void testEntityManager() throws Exception {
        for(EntityType type: em.getMetamodel().getEntities()){
            System.out.println(type.getName());
        }
        /*for(Object entity :mappingContext.getPersistentEntities()){
            System.out.println(((PersistentEntity)entity).getName());
        }*/
        Account account = new Account();
        account.setName("kcao");
        account.setPassword("kcao");
        ClassTypeInformation typeInformation = ClassTypeInformation.from(account.getClass());
        Class<?> type = typeInformation.getType();
        BeanInfo info = Introspector.getBeanInfo(type);
        for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
            System.out.println(descriptor.getName());
            System.out.println(descriptor.getDisplayName());
        }
        mappingContext.getPersistentEntity(account.getClass());
        for(Field field: account.getClass().getDeclaredFields()){
            System.out.println(field.getGenericType());
        }
        for(Method method: account.getClass().getDeclaredMethods()){
            System.out.println(method.getName()+" "+method.getGenericReturnType());
        }
        account = accounts.save(account);
        System.out.println(account.getClass());
    }

}

