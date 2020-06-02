package com.dc.elasticSearch.controller;

import com.dc.elasticSearch.entity.Blog;
import com.dc.elasticSearch.service.BlogRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author DC
 * @Date 2020-03-24
 */
@RestController
public class BlogTestController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private BlogRepository blogRepository;

    // 添加测试数据
    @GetMapping("/addTest")
    public String testElSearch() {
        //该list用于添加需要存入的数据
        List<IndexQuery> indexQueryList = new ArrayList<>();

        //模拟一些数据
        Blog blog = new Blog();
        blog.setId((long) new Random().nextInt(1500));
        blog.setMasterName("JCccc");
        blog.setArticleNum(10);
        blog.setCommentNum(29);
        blog.setThumbNum(100);
        blog.setDescription("分享不仅为了别人,也是为了自己");

        //把这个数据放入indexQueryList
        indexQueryList.add(new IndexQueryBuilder().withObject(blog).build());

        //循环模拟一些数据
        for (int i = 0; i <= 6; i++) {
            Blog blog2 = new Blog();
            blog2.setId((long) new Random().nextInt(1500));
            blog2.setMasterName("Test" + i * 2 * 3);
            blog2.setArticleNum(i * 60);
            blog2.setCommentNum(i * 16);
            blog2.setThumbNum(i * 500);
            blog2.setDescription("测试添加" + i);
            indexQueryList.add(new IndexQueryBuilder().withObject(blog2).build());
        }
        // 插入elasticsearch
        elasticsearchTemplate.bulkIndex(indexQueryList);
        return "add success";
    }

    //查询数据
    @GetMapping("/getTestData")
    public List<Blog> getTestData() {
        //模糊查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                // .withQuery(QueryBuilders.matchPhraseQuery("masterName", "JCccc"))
                .withQuery(QueryBuilders.matchPhraseQuery("masterName", "Test")) //模糊查询 masterName like Test的数据
                .build();
        List<Blog> list = elasticsearchTemplate.queryForList(searchQuery, Blog.class);
        return list;
    }

    //单个根据id查询
    @GetMapping("/queryTestDataOne")
    public String queryTestDataOne(String id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id);
        Blog blog = elasticsearchTemplate.queryForObject(getQuery, Blog.class);
        return blog.toString();
    }

    //根据单条件查询
    @GetMapping("/queryTestDataCondition")
    public List<Blog> queryTestDataCondition() {
        List<Blog> blogList = new ArrayList<>();
        TermQueryBuilder termQuery = new TermQueryBuilder("thumbNum", 1000);
        Iterable<Blog> iterable = blogRepository.search(termQuery);
        iterable.forEach(e -> blogList.add(e));
        return blogList;
    }

}
