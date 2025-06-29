package com.wx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class MyAspect {

    public static Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Pointcut("execution(* com.wx.service.impl.DeptServiceImpl.*(..))")
    public void pt() {
    }

    @Before("pt()")
    public void Before(JoinPoint joinPoint) {
        String name = joinPoint.getTarget().getClass().getName();//获取目标类名
        Signature signature = joinPoint.getSignature();//获取目标方法签名
        String methodName = joinPoint.getSignature().getName();//获取目标方法名
        Object[] args = joinPoint.getArgs();//获取目标方法运行参数
    }

    @Around("pt()")
    public void Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("MyAspect around before..");

        //1.获取 目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名{}", className);

        //2.获取 目标方法的方法名
        String signatureName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名{}", signatureName);

        //3.获取 目标方法运行时传入的参数名
        Object[] args = joinPoint.getArgs();
        log.info("目标方法运行时传入的参数名{}", Arrays.toString(args));

        //4.放行 目标方法执行
        Object result = joinPoint.proceed();

        //5.获取 目标方法运行的返回值
        log.info("目标方法执行{}", result);

        log.info("MyAspect after before..");
    }
}
