package com.hope.one.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.AtomicDouble;
import com.hope.one.common.Metrics;
import com.hope.one.mapper.BladeNoticeMapper;
import io.micrometer.core.instrument.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-26 17:40
 */
@Slf4j
@Component
public class PrometheusMonitorJob {

    @Resource
    private BladeNoticeMapper bladeNoticeMapper;
    @Autowired
    private MeterRegistry registry;

    private final AtomicDouble atomicVar = new AtomicDouble(0);

    //@Scheduled(cron = "0/50 * * * * ?")
    void doSomethingWith() {
        log.info("定时任务开始......");
        List<Metrics> metrics = bladeNoticeMapper.metrics();
        if (CollectionUtil.isEmpty(metrics)) {
            return;
        }
        double var = RandomUtil.randomDouble(400, 600);
        for (Metrics metric : metrics) {
            Meter meter = this.getMeter(metric.getName());
            //todo

            if (null == meter) {
                this.register(metric, var);
            } else {
                this.increment(meter, var);
            }
        }
        log.info("定时任务结束......");
    }

    /**
     * 从注册表中获取指标
     * 不允许注册已经有一个不同类型的同名注册仪表
     *
     * @param name 指标名称
     * @return
     */
    public Meter getMeter(String name) {
        List<Meter> meters = registry.getMeters().stream().filter(meter -> meter.getId().getName().equals(name)).collect(Collectors.toList());
        return CollUtil.isNotEmpty(meters) ? meters.get(0) : null;
    }

    /**
     * 注册指标
     *
     * @param metric 配置指标
     * @param var    默认值
     */
    public void register(Metrics metric, double var) {
        String type = metric.getType();
        switch (type) {
            case "counter":
                registry.counter(metric.getName()).increment(var);
                break;
            case "gauge":
                Tags tags = Tags.of
                (
                        "application=", "soa-mars-monitor-export-service",
                        "indicator_code", "indicator_micro_registers_users",
                        "indicator_name", "indicator_micro_registers_users_100"
                );
                registry.gauge("indicator_micro_registers_users_100", tags, atomicVar);
                atomicVar.set(var);
                break;
            case "distribution_summary":
                registry.summary(metric.getName()).record(var);
                break;
            default:
                break;
        }
    }

    /**
     * 更新指标值
     *
     * @param meter 注册指标对象
     * @param var   更新值
     */
    public void increment(Meter meter, double var) {
        Meter.Type meterType = meter.getId().getType();
        switch (meterType) {
            case COUNTER:
                ((Counter) meter).increment(var);
                break;
            case GAUGE:
                atomicVar.set(var);
                break;
            case DISTRIBUTION_SUMMARY:
                ((DistributionSummary) meter).record(var);
                break;
            default:
                break;
        }
    }

}
