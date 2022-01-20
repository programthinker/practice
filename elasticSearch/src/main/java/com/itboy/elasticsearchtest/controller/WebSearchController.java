package com.itboy.elasticsearchtest.controller;

import com.itboy.elasticsearchtest.entity.PageResponseEntity;
import com.itboy.elasticsearchtest.entity.RequestEntity;
import com.itboy.elasticsearchtest.entity.Result;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.controller
 * @ClassName: WebSearchController
 * @Date: 2021年08月06日 00:12
 * @Author: zhanggeyang
 * @Description:
 **/


@RestController
@RequestMapping(value = "/search")
public class WebSearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private SearchRequest searchRequest;

    @Autowired
    private SearchSourceBuilder searchSourceBuilder;

    @Autowired
    private HighlightBuilder highlightBuilder;
    /*
     * @Author: zhanggeyang
     * @Description:
     * @Date: 6:07 下午 8/6/21
     * @Param: [user, s]
     * @Return: com.itboy.elasticsearchtest.entity.Result
     */
    @PostMapping(value = "/aggration")
    public PageResponseEntity queryData(@RequestBody RequestEntity requestEntity) {

        searchRequest.indices("user");
        //需求：根据条件模糊查询，匹配年龄上下限，高亮，字段过滤，排序，分页
        //姓名模糊查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        TermQueryBuilder matchQueryBuilder = QueryBuilders.termQuery("name", requestEntity.getName());
        //BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(matchQueryBuilder);
        //年龄范围匹配
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
        rangeQueryBuilder.gte(requestEntity.getMin());
        rangeQueryBuilder.lte(requestEntity.getMax());
        boolQueryBuilder.must(rangeQueryBuilder);
        boolQueryBuilder.must(matchQueryBuilder);

        //BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", requestEntity.getName());
        //字段过滤
        //searchSourceBuilder.fetchSource(new String[]{"name", "age"}, new String[]{"address", "sex"});
        //boolQueryBuilder.filter(fuzzyQueryBuilder);


        //分页排序
        searchSourceBuilder.from(requestEntity.getCurrentPage()).size(requestEntity.getPageSize()).sort("age", SortOrder.valueOf(requestEntity.getSortOrder()));
        //高亮
        //highlightBuilder.preTags("<front color='red'>");
        //highlightBuilder.postTags("</front>");
        //highlightBuilder.field("title");
        //sourceBuilder.highlighter(highlightBuilder);
        //
        /*BoolQueryBuilder builder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", "zhang"));
        TermQueryBuilder termsQuery = QueryBuilders.termQuery("age", 3);


        sourceBuilder.query(builder);
        sourceBuilder.query(termsQuery);*/
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.query(matchQueryBuilder);
        //searchSourceBuilder.query(fuzzyQueryBuilder);
        searchSourceBuilder.query(rangeQueryBuilder);
        SearchRequest source = searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(source, RequestOptions.DEFAULT);
            long totalHits = searchResponse.getHits().getTotalHits();
            System.out.println(searchResponse.getHits().getTotalHits()+" 命中 ");
            ArrayList<Object> arrayList = new ArrayList<>();
            for (SearchHit hit : searchResponse.getHits()) {
                System.out.println(hit.getSourceAsMap());
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                arrayList.add(sourceAsMap);
            }
            return new PageResponseEntity(totalHits,arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PageResponseEntity();
    }

    @GetMapping(value = "/index")
    public Result user(@RequestParam String param) {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest request = new SearchRequest(param);
        request.source(searchSourceBuilder.query(QueryBuilders.matchAllQuery()));
        //searchRequest.indices(param);
        //searchRequest.source(sourceBuilder.query(QueryBuilders.matchAllQuery()));
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            ArrayList<Object> arrayList = new ArrayList<>();
            for (SearchHit hit : response.getHits()) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                arrayList.add(sourceAsMap);
            }
            return new Result(true,"success",arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(false,"failure");
    }

    @GetMapping(value = "/index1")
    public Result test(@RequestParam String param) {
        //RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        //SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //SearchRequest request = new SearchRequest(param);
        //request.source(searchSourceBuilder.query(QueryBuilders.matchAllQuery()));
        searchRequest.indices(param);
        searchRequest.source(searchSourceBuilder.query(QueryBuilders.matchAllQuery()));
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            ArrayList<Object> arrayList = new ArrayList<>();
            for (SearchHit hit : response.getHits()) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                arrayList.add(sourceAsMap);
            }
            return new Result(true,"success",arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(false,"failure");
    }

}
