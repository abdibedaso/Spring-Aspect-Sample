package com.geekcolab.development.SpringAspectSample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){}

    @Before("executeLogging()")
    public void logBeforeMethodCall(JoinPoint joinPoint){
        LOG.info("-------------------------------------------");
        LOG.info("Logging - with Before Advice");
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if(null!=args && args.length>0){
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg->{
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        LOG.info(message.toString());
        LOG.info("-------------------------------------------");
    }

    @AfterReturning(value = "executeLogging()", returning = "returnValue")
    public void logAfterReturningMethodCall(JoinPoint joinPoint, Object returnValue){
        LOG.info("-------------------------------------------");
        LOG.info("Logging - with AfterReturning Advice");
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if(null!=args && args.length>0){
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg->{
                message.append(arg).append(" | ");
            });
            message.append("]");
        }

        if(returnValue instanceof Collection){
            message.append(", returning: ").append(((Collection<?>) returnValue).size()).append(" instance(s)");
        } else{
            message.append(", returning: ").append(returnValue.toString());
        }

        LOG.info(message.toString());
        LOG.info("-------------------------------------------");
    }

    @Around(value = "executeLogging()")
    public Object logAfterReturningMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();

        LOG.info("-------------------------------------------");
        LOG.info("Logging - with Around Advice");
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());

        long totalTime = System.currentTimeMillis()-startTime;
        message.append(" totalTime: ").append(totalTime).append("ms");

        Object[] args = joinPoint.getArgs();
        if(null!=args && args.length>0){
            message.append(" args=[ | ");
            Arrays.asList(args).forEach(arg->{
                message.append(arg).append(" | ");
            });
            message.append("]");
        }

        if(returnValue instanceof Collection){
            message.append(", returning: ").append(((Collection<?>) returnValue).size()).append(" instance(s)");
        } else{
            message.append(", returning: ").append(returnValue.toString());
        }

        LOG.info(message.toString());
        LOG.info("-------------------------------------------");

        return returnValue;

    }

}