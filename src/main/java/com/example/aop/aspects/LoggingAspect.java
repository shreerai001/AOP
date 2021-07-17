package com.example.aop.aspects;

import com.example.aop.model.ActivityLogEntity;
import com.example.aop.model.ProgrammerEntity;
import com.example.aop.repository.ActivityLoggerRepository;
import com.example.aop.utils.MethodSignatureConst;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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


    @Pointcut("execution(public * *.*(..)) && within(com.example.aop.service.*),(com.example.aop.controller.*)")
    // A predicate that matches join points. Advice is associated
    // with a pointcut expression and runs at any join point matched by the pointcut
    public final void applicationPackagePointcut() {
        /*
         * This method defines where Pointcut should be present
         * Without this method @Pointcut expression should be explicitly put on @Around
         */
    }

    @Async
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "programmerEntityList")
    public void afterReturning(JoinPoint joinPoint, List<ProgrammerEntity> programmerEntityList) {
        log.debug("{}", joinPoint);
        log.info("result = {} \n size of return type={}", programmerEntityList, programmerEntityList.size());
    }


    @Async
    @Around("applicationPackagePointcut()") // Advice that surrounds a join point such as method invocation.
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("Entered:{}.{}() \n with arguments={}\n", methodName,
                joinPoint.getSignature().getName(),
                joinPoint.getArgs().length == 0 &&
                        joinPoint.getSignature().getName().equals(MethodSignatureConst.SAVE_PROGRAMMER)
                        ? joinPoint.getArgs()[0].toString() : Arrays.toString(joinPoint.getArgs())
        );
        persistLog(methodName);
//        try {
//            final Object result = joinPoint.proceed();
//            log.info("Exit: {}.{}() \n with result={}\n size of result={}", methodName,
//                    joinPoint.getSignature().getName(), joinPoint.getArgs().length, joinPoint.getArgs());
//            return result;
//        } catch (IllegalArgumentException illegalArgumentException) {
//            log.error("Illegal argument:{} in {}.{}()\n", Arrays.toString(joinPoint.getArgs()),
//                    methodName, joinPoint.getSignature().getName());
//            throw illegalArgumentException;
//        }
        return joinPoint.proceed();
    }

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("AspectJ Started:{} ", contextRefreshedEvent);
    }

    private void persistLog(final String methodName) {
        activityLoggerRepository.save(ActivityLogEntity.builder()
                .activity(methodName)
                .localDateTime(LocalDateTime.now())
                .build());
    }

    @Async
    @AfterThrowing(value = "applicationPackagePointcut()", throwing = "exception")
    public void logException(final JoinPoint joinPoint, final Exception exception) {
        log.error("{} at {}", exception.getMessage(), joinPoint.getSignature());
    }

}
