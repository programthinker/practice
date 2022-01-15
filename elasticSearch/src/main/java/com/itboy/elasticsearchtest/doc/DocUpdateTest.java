package com.itboy.elasticsearchtest.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.doc
 * @ClassName: DocInsertTest
 * @Date: 2021年08月05日 17:25
 * @Author: zhanggeyang
 * @Description:
 **/

public class DocUpdateTest {

    public static void main(String[] args) throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        UpdateRequest updateRequest = new UpdateRequest();
        UpdateRequest request = updateRequest.index("user-test").id("1001").doc(XContentType.JSON, "sex", "bunanbunv");
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getGetResult());

    }
}
