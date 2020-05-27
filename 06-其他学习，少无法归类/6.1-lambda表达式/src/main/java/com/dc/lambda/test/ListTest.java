package com.dc.lambda.test;

import com.dc.lambda.entity.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Description lambda 表达式遍历集合
 * @Author DC
 * @Date 2020-05-27
 */
public class ListTest {
    public static void main(String[] args) {

        //lambda 表达式遍历集合
       /* ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);
        System.out.println("lambda 表达式遍历集合：");
        list.forEach(System.out::println);
        System.out.println("lambda 表达式遍历集合---读取偶数：");
        list.forEach(element -> {
            if (element % 2 == 0) {
                System.out.println("偶数：" + element);
            }
        });*/


        // 删除集合中的某个元素
     /*   ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(11, "小牙刷", 12.05));
        items.add(new Item(5, "日本马桶盖", 999.05));
        items.add(new Item(7, "格力空调", 888.88));
        items.add(new Item(17, "肥皂", 2.00));
        items.add(new Item(9, "冰箱", 4200.00));
        //删除
        items.removeIf(ele -> ele.getId() == 7);
        //通过 foreach 遍历，查看是否已经删除
        System.out.println("删除集合中的某个元素：");
        items.forEach(System.out::println);*/


        //集合内元素的排序
        //在以前我们若要为集合内的元素排序，就必须调用 sort 方法，
        // 传入比较器匿名内部类重写 compare 方法，我们现在可以使用 lambda 表达式来简化代码。
        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(new Item(9, "背心", 7.80));
        itemList.add(new Item(7, "半袖", 37.80));
        itemList.add(new Item(8, "风衣", 139.80));
        itemList.add(new Item(6, "秋裤", 55.33));

        // 原本方式
       /* itemList.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });*/

        // Lambda 表达式方式
        itemList.sort((o1, o2) -> {
            return o1.getId() - o2.getId();
        });

        System.out.println(itemList);

    }
}
