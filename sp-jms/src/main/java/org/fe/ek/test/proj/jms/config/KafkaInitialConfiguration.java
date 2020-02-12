package org.fe.ek.test.proj.jms.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.fe.ek.test.proj.jms.cons.KafkaConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SimpleProj
 * @description: auto create kafka topic if not exist
 * @author: Wang Zhenhua
 * @create: 2020-02-12
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-12
 **/
@Configuration
public class KafkaInitialConfiguration {

    @Bean
    public NewTopic initialTopic() {
        return new NewTopic(KafkaConst.PORTRAIT_PACK_KAFKA_TOPIC,3,(short) 1);
    }
}
