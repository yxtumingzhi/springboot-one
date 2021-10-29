package com.hope.one.common;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-28 16:29
 */
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class Config {


    /**
     * sql 日志
     *
     * @return SqlLogInterceptor
     */
    @Bean
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }

}
