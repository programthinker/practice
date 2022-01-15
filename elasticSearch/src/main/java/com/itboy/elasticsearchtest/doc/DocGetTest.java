package com.itboy.elasticsearchtest.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.doc
 * @ClassName: DocInsertTest
 * @Date: 2021年08月05日 17:25
 * @Author: zhanggeyang
 * @Description:
 **/

public class DocGetTest {

    public static void main(String[] args) throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));

        GetRequest getRequest = new GetRequest();
        GetRequest request = getRequest.index("user").id("1003");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsMap());
        System.out.println(response.getSourceAsString());
        System.out.println(response.getSource());


    }
}
