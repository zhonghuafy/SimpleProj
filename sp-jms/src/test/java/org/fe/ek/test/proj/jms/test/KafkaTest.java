package org.fe.ek.test.proj.jms.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.jms.JmsApplication;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: SimpleProj
 * @description: KafkaTest
 * @author: Wang Zhenhua
 * @create: 2020-02-11
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JmsApplication.class)
@Slf4j
public class KafkaTest {

    private final String PORTRAIT_PACK_KAFKA_TOPIC = "PORTRAIT_ORDER_SIGN_PACKAGE_TOPIC1";

    @Autowired
    private BoPortraitGenerator boPortraitGenerator;

    @Test
    public void send() {
        BoPortraitModel model = boPortraitGenerator.portrait();
        String json = JSONObject.toJSONString(model);
        log.info("[KafkaTest] KafkaTest pushed to kafka successfully.");
    }
}
