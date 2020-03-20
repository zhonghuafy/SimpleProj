package org.fe.ek.test.proj.es.service;

import org.fe.ek.test.proj.es.dto.req.GsOrderStatRequest;
import org.fe.ek.test.proj.es.dto.res.GsOrderStatResponse;

import java.util.Map;

/**
 * @program: SimpleProj
 * @description: statistic/aggregation for es
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
public interface IEsStatisticService {

    /**
     * select gs, partner_id, count(*) from order_oos_* group by gs, partner_id
     * @param request
     * @return
     */
    Map<String, GsOrderStatResponse> groupByGsAndPartnerId(GsOrderStatRequest request);

    /**
     * select date, count(*) from order_oos_* group by date
     * @param request
     * @return
     */
    Map<String, Long> groupByDay(GsOrderStatRequest request);
}
