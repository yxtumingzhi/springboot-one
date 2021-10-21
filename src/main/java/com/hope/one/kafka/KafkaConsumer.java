package com.hope.one.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-12 9:12
 */
@Component
public class KafkaConsumer {

    // 消费监听
   // @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record) {

        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费1：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    //@KafkaListener(topics = {"topic1"})
    public void onMessage21(ConsumerRecord<?, ?> record) {

        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费21：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    // 消费监听
   // @KafkaListener(topics = {"ads_kudu2es"})
    public void onMessage2(ConsumerRecord<?, ?> record) {

        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费ads_kudu2es：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }
}