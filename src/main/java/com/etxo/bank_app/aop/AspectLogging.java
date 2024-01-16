package com.etxo.bank_app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* com.etxo.bank_app.mapping..*.*(..))")
    public void getAnyFromMapping(){}
    @Before("getAnyFromMapping()")
    public void logAnyCallsOfMapping(JoinPoint jp){
        LOGGER.debug("Mapper {} was invoked with parameters {}",
                jp.getSignature().getName(), Arrays.stream(jp.getArgs()).toList());
    }

    @Pointcut("execution(* com.etxo.bank_app.service..*.*(..))")
    public void getAnyFromServices(){}

    @Before("getAnyFromServices()")
    public void logAnyCallOfService(JoinPoint joinPoint) {
        LOGGER.debug("Method {} was invoked with parameter {}.",
                joinPoint.getSignature(),
                joinPoint.getArgs());
    }

    @AfterThrowing("getAnyFromServices()")
    public void logAfterThrowingException(JoinPoint joinPoint){
        LOGGER.debug("Method {} of {} threw an exception",
                joinPoint.getSignature(),
                joinPoint.getSignature().getDeclaringType());
    }
}
