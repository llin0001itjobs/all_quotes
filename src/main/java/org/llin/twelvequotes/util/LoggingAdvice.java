package org.llin.twelvequotes.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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

    @Before("execution (* org.llin.twelvequotes.controller.*.*(..))")
    public void beforeController(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Starting controller + method: " +  methodName + " " + className);
    }
    
    @After("execution (* org.llin.twelvequotes.controller.*.*(..))")
    public void afterController(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Ending controller + method: " +  methodName + " " + className);
    }
        
    @Before("execution (* org.llin.twelvequotes.service.*.*(..))")
    public void beforeService(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Starting service + method: " +  methodName + " " + className);
    }
    
    @After("execution (* org.llin.twelvequotes.service.*.*(..))")
    public void afterService(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Ending service + method: " +  methodName + " " + className);
    }
    
    @Before("execution (* org.llin.twelvequotes.util.*.*(..))")
    public void beforeUtility(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Starting utility + method: " +  methodName + " " + className);
    }
        
    @After("execution (* org.llin.twelvequotes.util.*.*(..))")
    public void afterUtility(JoinPoint joinPoint)  {
    	Signature signature = joinPoint.getSignature();
    	               
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName();
        
    	logger.debug("*=*=*=*Ending utility + method: " +  methodName + " " + className);
    }
}
