package com.itboy.elasticsearchtest.indices;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest
 * @ClassName: IndexInsertTest
 * @Date: 2021年08月05日 15:58
 * @Author: zhanggeyang
 * @Description:
 **/

public class IndexInsertTest {
    public static void main(String[] args) throws Exception{

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200)));
        CreateIndexRequest user = new CreateIndexRequest("user");
        CreateIndexResponse response = client.indices().create(user, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
        client.close();
    }
}
