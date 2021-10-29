package com.hope.one.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-29 11:41
 */
@Configuration
@ComponentScan(basePackages = "com.hope.one.aop")
// 开启AOP支持
// 该注解中会使用Import注解导入后置处理器及注册自定义Bean用来完成AOP功能
@EnableAspectJAutoProxy
public class AopConfig {
}
