package com.example.aop.aspects;

import com.example.aop.common.exceptions.CustomException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class SecurityAspect {

    @Pointcut("within(com.example.aop.service.*),(com.example.aop.controller.*)")
    // A predicate that matches join points. Advice is associated
    // with a pointcut expression and runs at any join point matched by the pointcut
    public final void applicationPackagePointcut() {
        /**
         * This method defines where Pointcut should be present
         * Without this method @Pointcut expression should be explicitly put on @Around
         */
    }

    @Before("applicationPackagePointcut()")
    public void isAuthorize() {
        if (SecurityContextHolder.getContext() == null
                || SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new CustomException("Unauthorized user", 403);
        }
    }
}
