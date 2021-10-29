package com.hope.one.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-29 11:20
 */
@Aspect
@Component
public class LogAspects {
    // 声明切点，表示切到com.wb.spring.aop.MatchCalculator下的所有方法
    @Pointcut("execution(public * com.hope.one.aop.Demo3.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        // 获取被增强的目标对象，然后获取目标对象的class
        Class<?> targetClass = pjp.getTarget().getClass();
        System.out.println("执行Around，被增强的目标类为：" + targetClass);
        // 方法名称
        String methodName = pjp.getSignature().getName();
        System.out.println("执行Around，目标方法名称为：" + methodName);
        // 目标方法的参数类型
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 目标方法的入参
        Object[] args = pjp.getArgs();
        System.out.println("执行Around，方法入参为：" + Arrays.toString(args));
        try {
            // 目标方法
            Method method = targetClass.getMethod(methodName, parameterTypes);
            System.out.println("执行Around，方法为：" + method);
            // 继续放行
            return pjp.proceed();
        } catch (Throwable e) {
            System.err.println("执行Around异常..." + e);
            return "error";
        }
    }

    /**
     * 前置通知
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + "运行Before... 参数为：" + Arrays.asList(args));
    }

    /**
     * 后置通知
     */
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "运行After...");
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "运行AfterReturning... 正常返回，结果为：" + result);
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "运行AfterThrowing... 异常信息：" + exception);
    }
}