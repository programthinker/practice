package com.itboy.elasticsearchtest.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.doc
 * @ClassName: DocInsertTest
 * @Date: 2021年08月05日 17:25
 * @Author: zhanggeyang
 * @Description:
 **/

public class DocBatchGetTest {

    public static void main(String[] args) throws Exception{

        //1、查询全部
        /*RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        SearchRequest searchRequest = new SearchRequest().indices("post1", "post2", "post3");
        SearchRequest request = searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "wangwu")));
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse);
        System.out.println(searchResponse.getAggregations());
        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println("1111111"+documentFields.getSourceAsString());
        }*/

        //2、
        /*RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        String[] strings = new String[10];
        for (int i = 0; i < 10; i++) {
            strings[i] = "post"+i;
        }
        SearchRequest searchRequest = new SearchRequest().indices(strings);
        SearchRequest request = searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "zhang")));

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse);

        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(documentFields.getSourceAsString());
        }*/


        //3、全亮查询，分页
        /*RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        String[] strings = new String[10];
        for (int i = 0; i < 10; i++) {
            strings[i] = "test"+i;
        }
        SearchRequest searchRequest = new SearchRequest().indices(strings);
        SearchRequest request = searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()).from(2).size(3));

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse);

        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(documentFields.getSourceAsString());
        }*/


        //4、字段排序，只能用数字、日期字段排序吗？？？
        /*RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        *//*String[] strings = new String[10];
        for (int i = 0; i < 10; i++) {
            strings[i] = "test-"+i;
        }*//*
        SearchRequest searchRequest = new SearchRequest().indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "zhang-"));
        searchSourceBuilder.from(2).size(3).sort("age", SortOrder.ASC);
        SearchRequest request = searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println(searchResponse);

        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(documentFields.getSourceAsString());
        }*/


        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));

        SearchRequest searchRequest = new SearchRequest().indices("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "ttttt"));
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gt(0).lt(9);
        searchSourceBuilder.query(rangeQueryBuilder);
        SearchRequest request = searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(documentFields.getSourceAsString());
        }

        client.close();

        //5、聚合查询
    }
}
