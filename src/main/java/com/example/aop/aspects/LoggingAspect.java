package com.example.aop.aspects;

import com.example.aop.model.ActivityLogEntity;
import com.example.aop.repository.ActivityLoggerRepository;
import com.example.aop.utils.MethodSignatureConst;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect // A modularization of a concern that cuts across multiple classes
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ActivityLoggerRepository activityLoggerRepository;

    /*
     * Join point is a point during the execution of a program, such as the execution of a method or the handling of
     * an exception
     */

    /*
     * Advice is a action taken by an aspect at a particular join point
     */

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.example.aop.service.*),(com.example.aop.controller.*)")
    // A predicate that matches join points. Advice is associated
    // with a pointcut expression and runs at any join point matched by the pointcut
    public final void applicationPackagePointcut() {
        /*
         * This method defines where Pointcut should be present
         * Without this method @Pointcut expression should be explicitly put on @Around
         */
    }


    @Around("applicationPackagePointcut()") // Advice that surrounds a join point such as method invocation.
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entered:{}.{}() \n with arguments={}\n", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs().length == 0 &&
                        joinPoint.getSignature().getName().equals(MethodSignatureConst.SAVE_PROGRAMMER)
                        ? joinPoint.getArgs()[0].toString() : Arrays.toString(joinPoint.getArgs())
        );
        activityLoggerRepository.save(ActivityLogEntity.builder()
                .activity(joinPoint.getSignature().getDeclaringTypeName())
                .localDateTime(LocalDateTime.now())
                .build());
        try {
            final Object result = joinPoint.proceed();
            log.info("Exit: {}.{}() \n with result={}\n", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("Illegal argument:{} in {}.{}()\n", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw illegalArgumentException;
        }
    }
}
