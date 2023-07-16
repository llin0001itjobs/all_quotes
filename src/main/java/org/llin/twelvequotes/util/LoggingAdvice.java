package org.llin.twelvequotes.util;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice implements MethodBeforeAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Override
    @Pointcut("execution org.llin.twelvequotes.*.*(..)")
    public void before(Method method, Object[] args, Object target) throws Throwable {
    	logger.info("Calling class + method: " +  method.getName().getClass().getName() + " " + method.getName());
    }
    
}