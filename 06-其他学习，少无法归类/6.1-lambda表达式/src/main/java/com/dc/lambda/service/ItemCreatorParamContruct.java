package com.dc.lambda.service;

import com.dc.lambda.entity.Item;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-27
 */
public interface ItemCreatorParamContruct {
    Item getItem(int id, String name, double price);

}
