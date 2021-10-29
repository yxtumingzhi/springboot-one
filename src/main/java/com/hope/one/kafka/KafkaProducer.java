package com.hope.one.kafka;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-12 9:08
 */
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private CollectorRegistry collectorRegistry;

    @RequestMapping(value = "/meter/prometheus", produces = "text/plain; version=0.0.4; charset=utf-8")
    public String prometheus() {
        try {
            Writer writer = new StringWriter();
            Enumeration<Collector.MetricFamilySamples> samples = this.collectorRegistry.metricFamilySamples();
            TextFormat.write004(writer, samples);
            return writer.toString();
        } catch (IOException var4) {
            throw new RuntimeException("Writing metrics failed", var4);
        }
    }

    @lombok.Data
    private static class Data {
        private List<DataItem> data;
    }

    @lombok.Data
    @AllArgsConstructor
    private static class DataItem {
        private String indicator_code;
        private String indicator_cycle;
        private Integer group_id;
        private Integer group_type;
        private Integer value;
    }


    // 发送消息
    @GetMapping("/kafka/normal")
    public void sendMessage1() throws IOException {
//        Writer writer = new StringWriter();
//        Enumeration<Collector.MetricFamilySamples> samples = collectorRegistry.metricFamilySamples();
//        TextFormat.write004(writer, samples);
//        System.out.println(writer.toString());
        String data = "{\n" +
                "    \"is_send\":0,\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"id\":\"indicator_micro_track_uv_002_1633881600000_total_2_58_261702_4252\",\n" +
                "            \"indicator_code\":\"indicator_micro_track_uv_004\",\n" +
                "            \"metric_code\":\"metric_micro_track_uv\",\n" +
                "            \"dimension_code\":\"025\",\n" +
                "            \"indicator_date\":\"2021-10-11\",\n" +
                "            \"indicator_cycle\":\"total\",\n" +
                "            \"group_id\":58,\n" +
                "            \"group_type\":2,\n" +
                "            \"page_id\":261702,\n" +
                "            \"product_id\":4252,\n" +
                "            \"value\":5,\n" +
                "            \"created_time\":1634009450000,\n" +
                "            \"updated_time\":1634009450000\n" +
                "        }\n" +
                "    ],\n" +
                "    \"pkNames\":[\n" +
                "        \"id\"\n" +
                "    ],\n" +
                "    \"type\":\"UPSERT\",\n" +
                "    \"index\":\"micro_indicator_event_mpviewscreen_uv\"\n" +
                "}";
        String data2 = "{\n" +
                "    \"is_send\":0,\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"id\":\"indicator_micro_track_uv_002_1633881600000_total_2_58_261702_4252\",\n" +
                "            \"indicator_code\":\"indicator_micro_add_car_nums_001\",\n" +
                "            \"metric_code\":\"metric_micro_track_uv\",\n" +
                "            \"dimension_code\":\"025\",\n" +
                "            \"indicator_date\":\"2021-10-11\",\n" +
                "            \"indicator_cycle\":\"total\",\n" +
                "            \"group_id\":58,\n" +
                "            \"group_type\":2,\n" +
                "            \"page_id\":261702,\n" +
                "            \"product_id\":4252,\n" +
                "            \"value\":[\"1025\",\"2025\",\"3025\"],\n" +
                "            \"created_time\":1634009450000,\n" +
                "            \"updated_time\":1634009450000\n" +
                "        }\n" +
                "    ],\n" +
                "    \"pkNames\":[\n" +
                "        \"id\"\n" +
                "    ],\n" +
                "    \"type\":\"UPSERT\",\n" +
                "    \"index\":\"micro_indicator_event_mpviewscreen_uv\"\n" +
                "}";
        //kafkaTemplate.send("ads_kudu2es", JSONObject.toJSONString(JSONObject.parseObject(data)));
       // kafkaTemplate.send("ads_kudu2es", JSONObject.toJSONString(JSONObject.parseObject(data2)));

        Data da = new Data();
        int[] nums = new int[]{1,3,4};
        int a = nums.length;
        HashMap<String,String> map = new HashMap<>();



        DataItem item = new DataItem("indicator_micro_track_uv_004","day",58,2,26);
        DataItem item2 = new DataItem("indicator_micro_track_uv_004","day",10062,8,11);
        DataItem item3 = new DataItem("indicator_micro_add_car_nums_001","day",4806,2,31);
        List<DataItem> list = Lists.newArrayList(item, item2, item3);
        da.setData(list);
        kafkaTemplate.send("ads_kudu2es", JSONObject.toJSONString(da));

        String vale = HttpUtil.get("http://localhost:5002/actuator/prometheus");
        System.out.println(vale);
    }
}
