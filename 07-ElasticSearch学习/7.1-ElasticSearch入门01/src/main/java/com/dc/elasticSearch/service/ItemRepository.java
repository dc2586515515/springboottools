package com.dc.elasticSearch.service;

import com.dc.elasticSearch.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-02
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    /**********************  SPring Data ElasticSearch自定义方法 *************************/
    /**
     * 根据价格区间查询
     *
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(double price1, double price2);

    /**
     * 根据名称|| 价格查询
     *
     * @param title
     * @param price
     * @return
     */
    List<Item> findByTitleOrPrice(String title, double price);


}
