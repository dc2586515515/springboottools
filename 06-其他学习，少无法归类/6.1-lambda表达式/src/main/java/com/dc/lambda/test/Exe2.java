package com.dc.lambda.test;

import com.dc.lambda.entity.Item;
import com.dc.lambda.service.ItemCreatorBlankConstruct;
import com.dc.lambda.service.ItemCreatorParamContruct;

/**
 * @Description 构造方法的引用
 * @Author DC
 * @Date 2020-05-27
 */
public class Exe2 {
    public static void main(String[] args) {
        /**
         * 一般我们需要声明接口，该接口作为对象的生成器，
         * 通过  类名::new  的方式来实例化对象，然后调用方法返回对象。
         */
        ItemCreatorBlankConstruct creator = () -> new Item();
        Item item = creator.getItem();

        ItemCreatorBlankConstruct creator2 = Item::new;
        Item item2 = creator2.getItem();

        ItemCreatorParamContruct creator3 = Item::new;
        Item item3 = creator3.getItem(112, "鼠标", 135.99);
    }
}