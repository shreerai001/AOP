package com.example.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // A modularization of a concern that cuts across multiple classes
@Component
public class LoggingAspect {
    /**
     * Join point is a point during the execution of a program, such as the execution of a method or the handling of
     * an exception
     */

    /**
     * Advice is a action taken by an aspect at a particular join point
     */

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.example.aop.service.*),(com.example.aop.controller.*)")
    // A predicate that matches join points. Advice is associated
    // with a pointcut expression and runs at any join point matched by the pointcut
    public final void applicationPackagePointcut() {
        /**
         * This method defines where Pointcut should be present
         * Without this method @Pointcut expression should be explicitly put on @Around
         */
    }

    @Around("applicationPackagePointcut()") // Advice that surrounds a join point such as method invocation.
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entered:{}.{}() \n with arguments={}\n", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        try {
            final Object result = joinPoint.proceed();
            log.info("Exit: {}.{}() with result={}\n", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("Illegal argument:{} in {}.{}()\n", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw illegalArgumentException;
        }
    }
}
