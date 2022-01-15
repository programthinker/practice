package com.itboy.elasticsearchtest.indices;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;

import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest
 * @ClassName: IndexInsertTest
 * @Date: 2021年08月05日 15:58
 * @Author: zhanggeyang
 * @Description:
 **/

public class IndexGetTest {
    public static void main(String[] args) throws Exception{

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("127.0.0.1",9200)));
        GetIndexRequest user = new GetIndexRequest("user");
        GetIndexResponse response = client.indices().get(user, RequestOptions.DEFAULT);
        System.out.println(response.getAliases());
        Set<String> strings = response.getMappings().keySet();
        Set<Map.Entry<String, MappingMetadata>> entries = response.getMappings().entrySet();

        for (String s : strings) {
            Map<String, Object> sourceAsMap = response.getMappings().get(s).getSourceAsMap();
            for (String s1 : sourceAsMap.keySet()) {
                System.out.println("-----11"+sourceAsMap.get(s1));
            }
            System.out.println("111111"+sourceAsMap);
        }


        client.close();
    }
}
