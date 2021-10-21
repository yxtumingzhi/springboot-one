//package com.hope.one.common;
//
///**
// * @author tumingzhi
// * @version 1.0
// * @date 2021-09-29 15:07
// */
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
///**
// * ElasticSearch 客户端配置
// *
// * @author geng
// * 2020/12/19
// */
//@Configuration
//public class RestClientConfig extends AbstractElasticsearchConfiguration {
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("192.168.19.129:9200")
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
//
