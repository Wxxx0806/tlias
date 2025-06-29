package com.wx.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
@Aspect//AOP类
public class TimeAspect {

    public static Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Around("execution(* com.wx.service.*.*(..))")//切入表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        //1.记录开始时间
        long start = System.currentTimeMillis();

        //2.调用原始方法运行
        Object proceed = joinPoint.proceed();

        //3.记录结束时间，统计方法耗时
        long end = System.currentTimeMillis();

        log.info(joinPoint.getSignature() + "方法执行耗时{}ms", end - start);
        return proceed;

    }
}
