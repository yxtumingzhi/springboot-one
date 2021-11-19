package com.hope.one.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //标注增强处理类（切面类）
@Component //交由Spring容器管理
public class AnnotationAspect {

	/*
    可自定义切点位置，针对不同切点，方法上的@Around()可以这样写ex：@Around(value = "methodPointcut() && args(..)")
    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation)")
    public void methodPointcut(){}

    @Pointcut(value = "@annotation(com.rq.aop.common.annotation.MyAnnotation2)")
    public void methodPointcut2(){}
    */

    //定义增强，pointcut连接点使用@annotation（xxx）进行定义
    @Around(value = "@annotation(around)") //around 与 下面参数名around对应
    public void processAuthority(ProceedingJoinPoint point, MyAnnotation around) throws Throwable {
        System.out.println("ANNOTATION welcome");
        System.out.println("ANNOTATION 调用方法：" + around.methodName());
        System.out.println("ANNOTATION 调用类：" + point.getSignature().getDeclaringTypeName());
        System.out.println("ANNOTATION 调用类名" + point.getSignature().getDeclaringType().getSimpleName());
        point.proceed(); //调用目标方法
        System.out.println("ANNOTATION login success");
    }
}

