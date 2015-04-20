package com.novbank.store.domain.base.resource;

import com.novbank.store.helper.SpringApplicationContextHolder;
import com.novbank.store.service.ResourceService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by CaoKe on 2015/4/19.
 */
@DependsOn("springApplicationContextHolder")
@Aspect
@Configuration
public class ResourceEntityAspect {
    @DeclareParents( value="(@ResourceEntity *)", defaultImpl = ResourceBacking.class)
    public static ResourceBacked resourceBacking;

    @Autowired
    protected ResourceService resources;

    @Pointcut("execution(* com.novbank.store.domain.base.resource.ResourceBacked+.get*()) && this(entity) && !execution(* com.novbank.store.domain.base.resource.ResourceBacked.get*())")
    public void entityFieldGet(ResourceBacked entity){ }

    @Pointcut("execution(* com.novbank.store.domain.base.resource.ResourceBacked+.set*(..)) && this(entity) && args(newVal) && !execution(* com.novbank.store.domain.base.resource.ResourceBacked.set*(..))")
    public void entityFieldSet(ResourceBacked entity, Object newVal){ }

    @Pointcut("execution(com.novbank.store.domain.base.resource.ResourceBacked+.new(..)) && !execution(com.novbank.store.domain.base.resource.ResourceBacked+.new(com.novbank.store.domain.graph.Resource)) && this(entity)")
    public void arbitraryUserConstructorOfResourceObject(ResourceBacked entity){}

    @After("arbitraryUserConstructorOfResourceObject(entity)")
    public void afterArbitraryUserConstructorOfResourceObject(ResourceBacked entity){
        if(entity.resource()==null){

        }
    }

    @Around("entityFieldGet(entity)")
    public Object aroundEntityFieldGet(ProceedingJoinPoint pjp,ResourceBacked entity) throws Throwable{
        System.out.println("Get "+pjp.getSignature().getName());
        return pjp.proceed();
    }

    @Around("entityFieldSet(entity,newVal)")
    public Object aroundEntityFieldSet(ProceedingJoinPoint pjp, ResourceBacked entity, Object newVal) throws Throwable{
        System.out.println("Set "+pjp.getSignature().getName());

        return //pjp.proceed(new Object[]{newVal});
                pjp.proceed();
    }

    public static ResourceEntityAspect aspectOf() {
        return SpringApplicationContextHolder.getApplicationContext().getBean(ResourceEntityAspect.class);
    }
}
