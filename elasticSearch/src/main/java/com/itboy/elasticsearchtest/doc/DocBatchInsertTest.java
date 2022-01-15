package com.itboy.elasticsearchtest.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itboy.elasticsearchtest.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.doc
 * @ClassName: DocBatchInsertTest
 * @Date: 2021年08月05日 19:00
 * @Author: zhanggeyang
 * @Description:
 **/

public class DocBatchInsertTest {

    public static void main(String[] args) throws Exception {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        BulkRequest bulkRequest = new BulkRequest();


        ObjectMapper mapper = new ObjectMapper();
        for (int i = 0; i < 10; i++) {
            User user = new User("zhang-", "nan-" + i, "shenzhen-" + i, i);
            String userString = mapper.writeValueAsString(user);
            IndexRequest post1 = new IndexRequest("test").id("id-" + i).source(userString, XContentType.JSON);
            bulkRequest.add(post1);
        }
        /*IndexRequest post1 = new IndexRequest("post1").id("1").source(XContentType.JSON);
        IndexRequest post2 = new IndexRequest("post2").id("2").source(XContentType.JSON);
        IndexRequest post3 = new IndexRequest("post3").id("3").source(XContentType.JSON);
        bulkRequest.add(post1);
        bulkRequest.add(post2);
        bulkRequest.add(post3);*/
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }
}
