package com.etxo.bank_app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private final Logger LOGGER = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* com.etxo.bank_app.mapping..*.*(..))")
    public void getAnyFromMapping(){}

}
