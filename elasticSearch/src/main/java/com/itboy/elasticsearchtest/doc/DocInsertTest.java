package com.itboy.elasticsearchtest.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itboy.elasticsearchtest.entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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

public class DocInsertTest {

    public static void main(String[] args) throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id("1004");

        User user = new User("wangwu444", "男33", "shenzhen777");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        indexRequest.source(s, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println(index.getResult());
        System.out.println(index.getId());

    }
}
