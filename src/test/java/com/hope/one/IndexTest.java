package com.hope.one;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.hope.one.common.Item;
import com.hope.one.es.ItemRepository;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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