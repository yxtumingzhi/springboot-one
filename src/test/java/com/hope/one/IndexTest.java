package com.hope.one;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.Lists;
import com.hope.one.es.Article;
import com.hope.one.es.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-11 10:15
 */
@SuppressWarnings("all")
@SpringBootTest
public class IndexTest {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testCreate() {
        redisTemplate.opsForList().rightPush("city_list", "shanghai");
        redisTemplate.opsForList().rightPush("city_list", "beijing");
        redisTemplate.opsForList().rightPush("city_list", "henan");
        redisTemplate.opsForList().rightPush("city_list", "hebei");
        redisTemplate.opsForList().rightPush("city_list", "xianggang");
        System.out.println(Double.parseDouble("1434702675580059726"));

        List<String> tags = Lists.newArrayList("有意向到店测试", "有意向上门测试");
        if (tags.contains("有意向到店")) {
            System.out.println("有意向到店");
        } else if (tags.contains("有意向上门")) {
            System.out.println("有意向上门");
        } else {
            System.out.println("asdfasdfasdf");
        }
        List<String> list = Lists.newArrayList("a", "b", "c");
        String join = list.stream().collect(Collectors.joining(","));
        System.out.println(join);
        System.out.println(JSON.parseArray("[\"1\",\"12\",\"12\",\"12\"]", String.class));
        System.out.println(String.join(",", JSON.parseArray("[\"1\",\"12\",\"12\",\"12\"]", String.class)));

        Byte by = (byte) 1;
        System.out.println(by == 0);
        System.out.println(by == 1);
    }

}