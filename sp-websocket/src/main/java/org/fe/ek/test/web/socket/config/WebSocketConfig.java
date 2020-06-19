package org.fe.ek.test.web.socket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: Test-Project
 * @description: WebSocketConfig
 * @author: Wang Zhenhua
 * @create: 2020-06-15
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-15
 **/
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
