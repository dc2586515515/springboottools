package com.dc.spider.task;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dc.spider.Entity.Movie;
import com.dc.spider.mapper.MovieMapper;
import com.dc.spider.util.GetJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

/**
 * 需要在测试中注入 spring容器组件（这里是mapper），必须要使用junit，
 * pom中去掉 <scope>标签即可在除test外的其他地方使用，使用注解@RunWith(SpringJUnit4ClassRunner.class)注解class类
 * SpringBoot测试的话，配合使用@SpringBootTest注解，starter-test maven中去掉<scope>标签
 *
 * 本例爬虫参考https://blog.csdn.net/qwe86314/article/details/95046472
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoubanMovie {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void testGetDouBanMovie() {

        List<Movie> all = movieMapper.findAll();
        if (all == null) {
            System.out.println("无数据");
        } else {
            all.forEach((item) -> {
                System.out.println(item.getTitle());
            });
        }

        int start;//每页多少条
        int total = 0;//记录数
        int end = 9979;//总共9979条数据
        for (start = 0; start <= end; start += 20) {
            try {

                String address = "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=" + start;

                JSONObject dayLine = new GetJson().getHttpJson(address, 1);

                System.out.println("start:" + start);
                JSONArray jsonArray = dayLine.getJSONArray("data");

                List<Movie> list = JSONUtil.toList(jsonArray, Movie.class);

                if (start <= end) {
                    System.out.println("已经爬取到底了");
                }
                if (list != null) {
                    for (Movie movie : list) {
                        // 保存到数据库
                        movieMapper.insert(movie);
                    }
                    total += list.size();
                }
                System.out.println("正在爬取中---共抓取:" + total + "条数据");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
