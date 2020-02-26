package org.fe.ek.test.proj.jms.productor;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.jms.cons.KafkaConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: SimpleProj
 * @description: SendKafka
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
@Component
@Slf4j
public class SendKafka {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * send data to kafka
     * @param data
     */
    public void sendData(String data) {
        if (!StringUtils.isEmpty(data)) {
            kafkaTemplate.send(KafkaConst.PORTRAIT_PACK_KAFKA_TOPIC,data);
            log.info("[SendKafka] data pushed to kafka successfully.");
        }
    }
}
