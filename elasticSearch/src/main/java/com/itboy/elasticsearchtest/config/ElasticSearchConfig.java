package com.itboy.elasticsearchtest.config;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName elasticsearchtest
 * @PackageName com.itboy.elasticsearchtest.config
 * @ClassName ElasticSearchConfig
 * @Author zhanggeyang
 * @Date 2021-08-09 18:39
 * @Description
 * @Version 1.0
 */

@Configuration
public class ElasticSearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
    }

    @Bean
    public SearchRequest searchRequest() {
        return new SearchRequest();
    }

    @Bean
    public SearchSourceBuilder searchSourceBuilder() {
        return new SearchSourceBuilder();
    }

    @Bean
    public HighlightBuilder highlightBuilder() {
        return new HighlightBuilder();
    }
}
