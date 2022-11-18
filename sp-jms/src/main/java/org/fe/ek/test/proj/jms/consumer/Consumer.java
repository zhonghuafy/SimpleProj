package org.fe.ek.test.proj.jms.consumer;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.jms.cons.KafkaConst;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: SimpleProj
 * @description: Consumer
 * @author: Wang Zhenhua
 * @create: 2020-02-12
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-12
 **/
@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = KafkaConst.PORTRAIT_PACK_KAFKA_TOPIC)
    public void onMessage(String message) {
        log.info("[SimpleProj.Consumer] kafka consumer receive message: {}", message);
        if (!StringUtils.isEmpty(message)) {
            BoPortraitModel data = JSONObject.parseObject(message,BoPortraitModel.class);
            log.info("[SimpleProj.Consumer] data id: {}, size: {}", data.getId(), data.getList().size());
        }
        log.info("[SimpleProj.Consumer] consumed message from kafka.");
    }
}
