package org.fe.ek.test.proj.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.fe.ek.test.common.util.DateFormatUtil;
import org.fe.ek.test.proj.es.dto.page.ElasticPage;
import org.fe.ek.test.proj.es.dto.req.GsOrderStatRequest;
import org.fe.ek.test.proj.es.dto.res.GsOrderStatResponse;
import org.fe.ek.test.proj.es.service.IEsStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SimpleProj
 * @description: EsStatisticService
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
@Service
@Slf4j
public class EsStatisticService implements IEsStatisticService {

    @Autowired
    private RestHighLevelClient client;

    /**
     * select gs, partner_id, count(*) from order_oos_*
     * where gs=gs time >= start and time <= end and (status=refuse or status=accept)
     * group by gs, partner_id
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, GsOrderStatResponse> groupByGsAndPartnerId(GsOrderStatRequest request) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //query parameters
            sourceBuilder.query(queryBuilder(request));
            // use size() to indicate how many term buckets should be returned, default is 10
            sourceBuilder.aggregation(AggregationBuilders.terms("gs_count").field("gs").subAggregation(AggregationBuilders.terms("part_count").field("partner_id").size(10)));
            SearchRequest searchRequest = new SearchRequest("order_oos_*");
            searchRequest.source(sourceBuilder);
            //call es and process result
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            ParsedLongTerms parsedComposite = aggregations.get("gs_count");
            List list = parsedComposite.getBuckets();
            //key: gs, value: count and partner_id list
            Map<String,GsOrderStatResponse> data = new HashMap<>();
            for (Object o : list) {
                ParsedLongTerms.ParsedBucket bucket = (ParsedLongTerms.ParsedBucket) o;
                String gskey = bucket.getKeyAsString();
                long  docCount = bucket.getDocCount();
                GsOrderStatResponse statisticsResponse = new GsOrderStatResponse();
                statisticsResponse.setGs(gskey);
                statisticsResponse.setDocCount(docCount);

                Aggregations sub = bucket.getAggregations();
                ParsedStringTerms parsedStringTerms = sub.get("part_count");
                List sublist = parsedStringTerms.getBuckets();
                List<String> partnerIdList = new ArrayList<>();
                for (Object subO : sublist) {
                    ParsedStringTerms.ParsedBucket subBucket = (ParsedStringTerms.ParsedBucket) subO;
                    String subkey = subBucket.getKeyAsString();
                    partnerIdList.add(subkey);
                }
                statisticsResponse.setPartnerIdList(partnerIdList);
                data.put(gskey, statisticsResponse);
            }
            log.info("{}", JSONObject.toJSONString(data));
            log.info("success");
            return data;
        }catch (IOException iex) {
            log.error("error: ", iex);
        }
        return null;
    }

    /**
     * select date, count(*) from order_oos_*
     * where gs=gs time >= start and time <= end and (status=refuse or status=accept)
     * group by date
     * use Date histogram aggregation
     * see: https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-datehistogram-aggregation.html
     * @param request
     * @return
     */
    @Override
    public Map<String, Long> groupByDay(GsOrderStatRequest request) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

            sourceBuilder.query(queryBuilder(request));
            //aggregation
            //use minDocCount() to filter no document (docCount=0) records
            sourceBuilder.aggregation(AggregationBuilders.dateHistogram("date_count").calendarInterval(DateHistogramInterval.DAY).field("time").minDocCount(1).timeZone(ZoneId.systemDefault()));
            SearchRequest searchRequest = new SearchRequest("order_oos_*");
            searchRequest.source(sourceBuilder);
            //call es and process result
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            //key: date, value: count
            Map<String, Long> data = new HashMap<>();
            ParsedDateHistogram parsedComposite = aggregations.get("date_count");
            List list = parsedComposite.getBuckets();
            for (Object obj : list) {
                ParsedDateHistogram.ParsedBucket bucket = (ParsedDateHistogram.ParsedBucket) obj;
                String date = bucket.getKeyAsString();
                date = date.substring(0,10);//2019-10-17T00:00:00.000+0000 -> 2019-10-17
                long docCount = bucket.getDocCount();
                data.put(date, docCount);
            }
            log.info("{}", JSONObject.toJSONString(data));
            log.info("success");
            return data;
        }catch (IOException iex) {
            log.error("error: ", iex);
        }
        return null;
    }

    /**
     * query by page
     * select * from order_oos_*
     * where gs=gs time >= start and time <= end and (status=refuse or status=accept)
     * @param request
     * @param page
     * @return json format documents in collection
     */
    @Override
    public List<String> listDoc(GsOrderStatRequest request, ElasticPage page) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(queryBuilder(request)).from(page.getFrom()).size(page.getSize());
            SearchRequest searchRequest = new SearchRequest();
//        CollapseBuilder collapseBuilder = new CollapseBuilder("");//字段折叠,这里不需要
            searchRequest.indices("order_oos_*");
            searchRequest.source(sourceBuilder);
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<String> result = new ArrayList<>();
            for (SearchHit hit : hits) {
                result.add(hit.getSourceAsString());
            }
            return result;
        } catch (IOException iex) {
            log.error("error: ", iex);
        }
        return null;
    }

    /**
     * select count(*) from order_oos_*
     * where gs=gs time >= start and time <= end and (status=refuse or status=accept)
     * @param request
     * @return
     */
    @Override
    public Long count(GsOrderStatRequest request) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            CountRequest countRequest = new CountRequest();
            sourceBuilder.query(queryBuilder(request));
            countRequest.indices("order_oos_*");
            countRequest.source(sourceBuilder);
            CountResponse response = client.count(countRequest, RequestOptions.DEFAULT);
            return response.getCount();
        } catch (IOException iex) {
            log.error("error: ", iex);
        }
        return 0L;
    }

    /**
     * set query conditions
     * where gs=gs time >= start and time <= end and status in (refuse, accept)
     *
     * @param request
     * @return
     */
    private BoolQueryBuilder queryBuilder(GsOrderStatRequest request) {
        //query parameters
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("gs",request.getGs()));
        boolQueryBuilder.must(
                QueryBuilders.rangeQuery("time")
                        .from(DateFormatUtil.format(request.getStart(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"))
                        .to(DateFormatUtil.format(request.getEnd(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ")));
        BoolQueryBuilder shouldBuilder = QueryBuilders.boolQuery();
        shouldBuilder.should(QueryBuilders.matchQuery("status", "refuse"));
        shouldBuilder.should(QueryBuilders.matchQuery("status", "accept"));
        boolQueryBuilder.must(shouldBuilder);
        return boolQueryBuilder;
    }
}
