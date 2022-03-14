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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @PostMapping("/kafka/normal")
    public void sendMessage1(@RequestBody Map map) throws IOException {
//        Writer writer = new StringWriter();
//        Enumeration<Collector.MetricFamilySamples> samples = collectorRegistry.metricFamilySamples();
//        TextFormat.write004(writer, samples);
//        System.out.println(writer.toString());
        String data = "{\"utm_service_type_name\":\"售后\",\"updated_time\":1646983738007,\"order_type_name\":\"保养\",\"mc_car_factory_id\":1057,\"group_type\":2,\"mc_car_id\":2268758,\"clue_invite_user_id\":644,\"clue_invite_user_type\":\"客户\",\"user_type\":\"客户\",\"user_mobile\":\"18111111992\",\"id\":1502179450542915588,\"mc_car_mileage\":22,\"order_type\":1,\"mc_car_brand_type_id\":23500,\"created_time\":1646983738007,\"clue_invite_user_name\":\"李逵\",\"clue_describe\":\"保养订单\",\"fact_id\":1570,\"group_name\":\"东风风神武汉测试店\",\"mc_car_age\":1895,\"mc_car_brand_id\":620,\"clue_name\":\"order_maintenance\",\"mc_car_series_id\":1174,\"utm_content_name\":\"维保\",\"user_id\":6336,\"event_name\":\"mic_order_mnt_paid\",\"order_id\":1570,\"utm_source\":3,\"code\":\"8c4e9231768c2227bf86e8aa5052af75\",\"clue_type_name\":\"客户主动意向\",\"mc_car_year_id\":23493,\"user_register_time\":1598520912727,\"order_number\":\"M1811075C140000511\",\"mc_car_vin\":\"JTJJM7FX3A5026325\",\"mc_car_model_id\":23500,\"app_code\":\"100\",\"utm_content\":\"2610\",\"mc_car_number\":\"沪G88888\",\"register_invite_user_type\":\"自然流量\",\"utm_service_type\":1,\"utm_source_name\":\"小程序\",\"app_name\":\"微信站点\",\"group_id\":58,\"clue_type\":5,\"event_time\":1646982613000,\"mc_car_brand_type\":\"大众/一汽大众/捷达/2017款/1.4 手动 时尚型\"}";
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
        if (map.get("page").equals("0") || map.get("page").equals("2")) {
            kafkaTemplate.send("ads_crm_interaction_topic", JSONObject.toJSONString(JSONObject.parseObject(data)));
        }
        if (map.get("page").equals("1") || map.get("page").equals("2")) {
            kafkaTemplate.send("clues_message", JSONObject.toJSONString(JSONObject.parseObject(data)));
        }
        // kafkaTemplate.send("ads_kudu2es", JSONObject.toJSONString(JSONObject.parseObject(data2)));

        Data da = new Data();
        int[] nums = new int[]{1, 3, 4};
        int a = nums.length;


        DataItem item = new DataItem("indicator_micro_track_uv_004", "day", 58, 2, 26);
        DataItem item2 = new DataItem("indicator_micro_track_uv_004", "day", 10062, 8, 11);
        DataItem item3 = new DataItem("indicator_micro_add_car_nums_001", "day", 4806, 2, 31);
        List<DataItem> list = Lists.newArrayList(item, item2, item3);
        da.setData(list);
        // kafkaTemplate.send("ads_crm_interaction_topic", JSONObject.toJSONString(da));

        //String vale = HttpUtil.get("http://localhost:5002/actuator/prometheus");
        //System.out.println(vale);
    }
}
