package com.anyi.reggie.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.anyi.reggie.service..*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 方法信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String fullMethod = className + "." + methodName;

        // 参数名和值
        String[] paramNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        System.out.println("✅ AOP @Around 开始");
        System.out.println("➡ 方法: " + fullMethod);
        System.out.println("➡ 参数:");

        for (int i = 0; i < paramNames.length; i++) {
            System.out.println("   - " + paramNames[i] + " = " + args[i]);
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            System.out.println("✅ 方法正常返回，耗时: " + duration + " ms");
            System.out.println("⬅ 返回值: " + result);
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - start;
            System.out.println("❌ 方法抛出异常，耗时: " + duration + " ms");
            System.out.println("❌ 异常类型: " + ex.getClass().getSimpleName());
            System.out.println("❌ 异常信息: " + ex.getMessage());
            throw ex; // 继续抛出异常给外层处理
        }

        System.out.println("✅ AOP @Around 结束");
        return result;
    }
}

