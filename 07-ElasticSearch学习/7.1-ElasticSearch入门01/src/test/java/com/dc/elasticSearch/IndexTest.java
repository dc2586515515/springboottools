package com.dc.elasticSearch;

import com.dc.elasticSearch.entity.Item;
import com.dc.elasticSearch.service.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApp.class)
public class IndexTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemRepository itemRepository;

    // 创建索引  &&  配置映射
    @Test
    public void testCreate() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }

    // 删除索引
    @Test
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }


    /***************************  Spring Data ElasticSearch 默认实现方法   ****************************************/

    //新建 索引库&&文档
    @Test
    public void createDocument() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");

        itemRepository.save(item);
    }

    //批量新增
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        itemRepository.saveAll(list);
    }

    /**
     * 修改，与新增同一个接口，若Id在ES中存在，则修改
     */
    @Test
    public void update() {
        Item item = new Item(1L, "小米手机7-update", " 手机-update",
                "小米-update", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll() {
        Iterable<Item> items = itemRepository.findAll();
        items.forEach((item) -> System.out.println(item));
    }


    /***************************  Spring Data ElasticSearch 默认实现方法  结束  ****************************************/


    /***************************  Spring Data ElasticSearch  自定义方法 ****************************************/

    /**
     * 查询价格区间
     */
    @Test
    public void queryByPriceBetween() {
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        list.forEach(item -> System.out.println(item));
    }

    /**
     * 根据名称或价格查询
     */
    @Test
    public void findByTitleOrPrice() {
        List<Item> items = itemRepository.findByTitleOrPrice("小米", 2799);
        items.forEach(item -> System.out.println(item));
    }

    // 高级查询
    @Test
    public void testQuery() {
        // 词条查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
        Iterable<Item> items = itemRepository.search(queryBuilder);
        items.forEach(item -> System.out.println(item));
    }

    /**
     * 利用NativeSearchQueryBuilder可以实现分页
     */
    @Test
    public void testNativeQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米"));
        // 执行搜索，获取结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        items.forEach(System.out::println);
    }


    /**
     * 排序也通用通过NativeSearchQueryBuilder完成：
     */
    @Test
    public void testSort() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));

        // 执行搜索，获取结果
        Iterable<Item> items = itemRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(((Page<Item>) items).getTotalElements());
        items.forEach(System.out::println);
    }

    /**
     * 聚合为桶
     * 桶就是分组，比如这里我们按照品牌brand进行分组
     */
    @Test
    public void testAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"));

        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) itemRepository.search(queryBuilder.build());

        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        buckets.forEach(bucket -> {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        });
    }

    /**
     * 嵌套聚合，求平均值
     */
    @Test
    public void testSubAgg() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
        queryBuilder.addAggregation(
                AggregationBuilders.terms("testAgg").field("brand")
                        // 在品牌聚合桶内进行嵌套聚合，求平均值
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price"))
        );

        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("testAgg");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
            System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");
            // 3.6.获取子聚合结果：
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
            System.out.println("平均售价：" + avg.getValue());
        }

    }
}
