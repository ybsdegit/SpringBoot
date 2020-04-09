package com.ybs.es;

import com.alibaba.fastjson.JSON;
import com.ybs.es.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * es 7.6 高级客户端测试
 */
@SpringBootTest
class SpringElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {
    }

    // 创建索引
    @Test
    void testCreteIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("ybs_index");

        // 2、执行创建请求 请求后获取响应
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    // 获取索引 判断是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("ybs_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("testdb");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }


    // 添加文档
    @Test
    void testAddDocument() throws IOException {
        // 创建对象
        User user = new User("元宝森", 3);
        // 创建请求
        IndexRequest request = new IndexRequest("ybs_index");
        // 设置规则  put /ybs_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);

        // 客户端发送请求 获取响应结果
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());  //CREATED
    }

    // 获取文档 判断文档是否存在
    @Test
    void testIsExists() throws IOException {
        GetRequest request = new GetRequest("ybs_index", "1");
        // 不获取返回的 _source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获取文档信息
    @Test
    void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("ybs_index", "1");
        GetResponse getResponse = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());  // 打印文档内容
        System.out.println(getResponse);
    }

    // 更新文档信息
    @Test
    void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("ybs_index", "1");
        request.timeout("1s");
        User user = new User("狂神说Java", 18);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse getResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(getResponse.status());
        System.out.println(getResponse);
    }

    // 删除文档信息
    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("ybs_index", "1");
        request.timeout("1s");
        DeleteResponse getResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(getResponse.status());
        System.out.println(getResponse);
    }


    // 特殊的，批量插入数据
    @Test
    void testBulkAddDocument() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("1s");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("元宝森1", 3));
        userList.add(new User("元宝森2", 4));
        userList.add(new User("元宝森3", 4));
        userList.add(new User("元宝森4", 5));
        userList.add(new User("元宝森5", 6));
        userList.add(new User("元宝森6", 77));
        userList.add(new User("元宝森7", 8));
        userList.add(new User("元宝森8", 99));
        userList.add(new User("元宝森9", 111));

        // 批处理请求
        for (int i = 0; i < userList.size(); i++) {
            // 批量更新和批量删除在这里修改对应的请求
            bulkRequest.add(new IndexRequest("ybs_index")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); // 是否失败
    }


    // 查询
    // 查询    
    // SearchRequest 搜索请求    
    // SearchSourceBuilder 条件构造    
    //  HighlightBuilder 构建高亮    
    //  TermQueryBuilder 精确查询    
    //  MatchAllQueryBuilder     
    //  xxx QueryBuilder 对应我们刚才看到的命令！
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("ybs_index");
        // 构建搜索条件        
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.highlighter();
        // 查询条件，我们可以使用 QueryBuilders 工具来实现        
        // QueryBuilders.termQuery 精确        
        // QueryBuilders.matchAllQuery() 匹配所有        
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "元宝森8");
        //MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();        
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }


    }

    //
    void test() {

    }


}
