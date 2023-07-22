package org.llin.twelvequotes.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Before("execution (* org.llin.twelvequotes.*.*(..))")
    public void before(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	
    	logger.info("Calling class + method: " +  signature.getClass().getName() + " " + signature.getName());
    }
    
}