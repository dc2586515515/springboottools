package com.dc.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    //id
    private int id;
    //名称
    private String name;
    // 价格
    private double price;
}
