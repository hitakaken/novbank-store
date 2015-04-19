package com.novbank.store.domain.base.resource;

import com.novbank.store.helper.SpringApplicationContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Created by CaoKe on 2015/4/19.
 */
@DependsOn("springApplicationContextHolder")
@Aspect
@Configuration
public class ResourceEntityAspect {
    //@DeclareParents( value="(@ResourceEntity *)")
    //public static ResourceSupport resource;

    public static ResourceEntityAspect aspectOf() {
        return SpringApplicationContextHolder.getApplicationContext().getBean(ResourceEntityAspect.class);
    }
}
