package com.dc.elasticSearch.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-02
 */
@Data
@Document(indexName = "testblog", type = "blogs")
public class Blog {
    /**
     * @Document中 indexName  索引名称，其实相当于咱们的数据库名称 ，必须为小写，
     * 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
     * <p>
     * 而type：类型 ，其实相当于咱们的数据库表的名称
     */
    private Long id;
    private String masterName;
    private Integer articleNum;
    private Integer commentNum;
    private Integer thumbNum;
    private String description;
}
