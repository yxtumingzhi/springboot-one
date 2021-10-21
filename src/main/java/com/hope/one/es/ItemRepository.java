package com.hope.one.es;

import com.hope.one.common.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-11 10:25
 */
@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {

}