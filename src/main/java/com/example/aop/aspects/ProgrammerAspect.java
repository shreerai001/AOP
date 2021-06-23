package com.example.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProgrammerAspect {

    private static final Logger log = LoggerFactory.getLogger(ProgrammerAspect.class);

    @Pointcut("within(com.example.aop.service.*)")
    public final void applicationPackagePointcut() {
        /**
         * This method defines where Pointcut should be present
         * Without this method @Pointcut expression should be explicitly put on @Around
         */
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entered:{}.{}() with arguments[s]={}\n", joinPoint.getSignature().getDeclaringTypeName(),
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
