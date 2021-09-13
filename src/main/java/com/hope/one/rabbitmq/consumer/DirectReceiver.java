package com.hope.one.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tumingzhi
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "direct_queue_simple")
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "direct_queue_order")
    @RabbitHandler
    public void process2(String testMessage) {
        System.out.println("DirectReceiver2消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout_exchange_queue_a")
    @RabbitHandler
    public void process3(Map testMessage) {
        long a = System.currentTimeMillis();
        System.out.println("fanout_exchange_queue_a process3消费者收到消息  : " + testMessage.toString());
        long b = System.currentTimeMillis();
        System.out.println(b - a + "fanout_exchange_queue_a耗时" + "");
    }


    @RabbitListener(queues = "fanout_exchange_queue_b")
    @RabbitHandler
    public void process32(Map testMessage) {
        System.out.println("消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "fanout_exchange_queue_c")
    @RabbitHandler
    public void process31(Map testMessage) {
        System.out.println("消费者收到消息  : " + testMessage.toString());
    }
}
