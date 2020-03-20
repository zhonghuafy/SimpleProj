package org.fe.ek.test.proj.es.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.es.dto.req.GsOrderStatRequest;
import org.fe.ek.test.proj.es.dto.res.GsOrderStatResponse;
import org.fe.ek.test.proj.es.service.impl.EsStatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * @program: SimpleProj
 * @description: ESTest
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ESTest {

    @Autowired
    private EsStatisticService statisticService;

    /**
     * test GroupGs
     */
    @Test
    public void testGroupGs() {
        GsOrderStatRequest request = new GsOrderStatRequest();
        request.setStart(Date.from(LocalDate.of(2019, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setEnd(Date.from(LocalDate.of(2019, 11, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setIsGroupDate(true);
        Map<String, GsOrderStatResponse> result = statisticService.groupByGsAndPartnerId(request);
        log.info("result: {}", JSONObject.toJSONString(result));
    }

    /**
     * test groupByDay
     */
    @Test
    public void testGroupDate() {
        GsOrderStatRequest request = new GsOrderStatRequest();
        request.setStart(Date.from(LocalDate.of(2019, 10, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setEnd(Date.from(LocalDate.of(2019, 11, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        request.setGs("610047");
        request.setIsGroupDate(true);
        Map<String, Long> result = statisticService.groupByDay(request);
        log.info("result: {}", JSONObject.toJSONString(result));
    }
}
