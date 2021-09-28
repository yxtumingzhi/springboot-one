package com.hope.one.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-27 9:41
 */
@RestController
@RequestMapping("/gateway/metrics")
public class GrafanaTestController {
    @Autowired
    private MeterRegistry meterRegistry;
    private Counter counter;

    @PostConstruct
    public void init() {
        Tags tags = Tags.of("common", "test");
        // 公共标签
        meterRegistry.config().commonTags(tags);
        counter = Counter.builder("metrics.request.common").register(meterRegistry);
    }

    /**
     * 订单请求测试
     */
    @GetMapping("/order/{appId}")
    public String orderTest(@PathVariable("appId") String appId) {
        counter.increment();
        return "ok";
    }

    /**
     * 产品请求测试
     */
    @GetMapping("/product/{appId}")
    public String productTest(@PathVariable("appId") String appId) {
        counter.increment();
        return "ok";
    }
}
