package cn.itcast.jd.service;

import cn.itcast.jd.pojo.Item;

import java.util.List;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-28
 */
public interface ItemService {
    /**
     * 保存商品
     *
     * @param item
     */
    public void save(Item item);

    /**
     * 根据条件查询商品
     *
     * @param item
     * @return
     */
    public List<Item> findAll(Item item);
}
