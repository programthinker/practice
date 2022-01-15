package com.itboy.elasticsearchtest.indices;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest
 * @ClassName: IndexInsertTest
 * @Date: 2021年08月05日 15:58
 * @Author: zhanggeyang
 * @Description:
 **/

public class IndexUpdateTest {
    public static void main(String[] args) throws Exception{

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200)));
        IndexRequest indexRequest = new IndexRequest();
        System.out.println(indexRequest.index());


        client.close();
    }
}
