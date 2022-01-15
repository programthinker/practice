package com.itboy.elasticsearchtest.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
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

public class DocDeleteTest {

    public static void main(String[] args) throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));

        DeleteRequest deleteRequest = new DeleteRequest();
        DeleteRequest request = deleteRequest.index("user-test").id("1003");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }
}
