//package com.hope.one.kafka;
//
//import io.prometheus.client.Collector;
//import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.exporter.common.TextFormat;
//import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
//import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
//import org.springframework.lang.Nullable;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.util.Enumeration;
//import java.util.Set;
//
///**
// * @author tumingzhi
// * @version 1.0
// * @date 2021-10-13 10:05
// */
//@WebEndpoint(id = "prometheus")
//public class Prometheus {
//
//    private final CollectorRegistry collectorRegistry;
//
//    public Prometheus(CollectorRegistry collectorRegistry) {
//        this.collectorRegistry = collectorRegistry;
//    }
//
//    @ReadOperation(
//            produces = {"text/plain; version=0.0.4; charset=utf-8"}
//    )
//    public String scrape(@Nullable Set<String> includedNames) {
//        try {
//            Writer writer = new StringWriter();
//            Enumeration<Collector.MetricFamilySamples> samples = includedNames != null ? this.collectorRegistry.filteredMetricFamilySamples(includedNames) : this.collectorRegistry.metricFamilySamples();
//            TextFormat.write004(writer, samples);
//            return writer.toString();
//        } catch (IOException var4) {
//            throw new RuntimeException("Writing metrics failed", var4);
//        }
//    }
//
//}
