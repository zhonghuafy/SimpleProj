package org.fe.ek.test.proj.jms.productor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: SimpleProj
 * @description: Productor
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
@Component
@Slf4j
public class Producer implements Runnable {
    @Autowired
    private BoPortraitGenerator boPortraitGenerator;

    @Autowired
    private SendKafka sendKafka;

    @Override
    public void run() {
        while (true) {
            try {
                BoPortraitModel data = boPortraitGenerator.portrait();
                log.info("[Producer] product a request package with 1000 order info");
                sendTokafka(data);
                Thread.sleep(2000);
            }catch (Exception ex) {
                log.error("[Producer] error to produce data. {}",ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void sendTokafka(BoPortraitModel data) {
        if (data != null) {
            String json = JSONObject.toJSONString(data);
            sendKafka.sendData(json);
        }
    }
}
