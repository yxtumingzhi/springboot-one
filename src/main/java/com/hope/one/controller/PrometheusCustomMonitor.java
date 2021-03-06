package com.hope.one.controller;

import cn.hutool.core.util.RandomUtil;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-26 17:40
 */
@Component
public class PrometheusCustomMonitor {

    /**
     * 订单发起次数
     */
    private Counter orderCount;

    /**
     * 金额统计
     */
    private DistributionSummary amountSum;

    private Gauge gauge;


    private final MeterRegistry registry;

    @Autowired
    public PrometheusCustomMonitor(MeterRegistry registry) {
        this.registry = registry;
    }

   // @PostConstruct
    private void init() {
        orderCount = registry.counter("order_request_count", "order", "test-svc");
        amountSum = registry.summary("order_amount_sum", "orderAmount", "test-svc");

    }

    public Counter getOrderCount() {
        return orderCount;
    }

    public DistributionSummary getAmountSum() {
        return amountSum;
    }
}