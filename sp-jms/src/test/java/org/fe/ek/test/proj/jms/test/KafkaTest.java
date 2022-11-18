package org.fe.ek.test.proj.jms.test;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.jms.JmsApplication;
import org.fe.ek.test.proj.jms.cons.KafkaConst;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: SimpleProj
 * @description: KafkaTest
 * @author: Wang Zhenhua
 * @create: 2020-02-11
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-11
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JmsApplication.class)
@Slf4j
public class KafkaTest {

    @Autowired
    private BoPortraitGenerator boPortraitGenerator;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void send() {
        BoPortraitModel model = boPortraitGenerator.portrait();
        String json = JSONObject.toJSONString(model);
        kafkaTemplate.send(KafkaConst.PORTRAIT_PACK_KAFKA_TOPIC,json);
        log.info("[KafkaTest] KafkaTest pushed to kafka successfully.");
    }
}
