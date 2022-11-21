package org.fe.ek.test.common.util.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SimpleProj
 * @description: RestTemplate Configuration
 * @author: Wang Zhenhua
 * @create: 2020-05-28
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-05-28
 **/
@Configuration
public class RestTemplateConfiguration {

    /**
     * Set the underlying URLConnection's connect timeout (in milliseconds).
     */
    @Value("${restTemplate.connection.timeout:1000}")
    private int REMOTE_CONN_TIMEOUT;

    /**
     * Set the underlying URLConnection's read timeout (in milliseconds).
     */
    @Value("${restTemplate.read.timeout:1000}")
    private int REMOTE_READ_TIMEOUT;

    /**
     * Indicates that a method produces a bean to be managed by the Spring container.<br>
     *   Synchronous client to perform HTTP requests, exposing a simple, template
     *   method API over underlying HTTP client libraries such as the JDK
     *   {@code HttpURLConnection}, Apache HttpComponents, and others.
     *
     *   <p>The RestTemplate offers templates for common scenarios by HTTP method, in
     *   addition to the generalized {@code exchange} and {@code execute} methods that
     *   support of less frequent cases.
     *
     *   <p><strong>NOTE:</strong> As of 5.0, the non-blocking, reactive
     *   {@code org.springframework.web.reactive.client.WebClient} offers a
     *   modern alternative to the {@code RestTemplate} with efficient support for
     *   both sync and async, as well as streaming scenarios. The {@code RestTemplate}
     *   will be deprecated in a future version and will not have major new features
     *   added going forward. See the WebClient section of the Spring Framework reference
     *   documentation for more details and example code.
     * @return
     */
//    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(REMOTE_CONN_TIMEOUT);
        factory.setReadTimeout(REMOTE_READ_TIMEOUT);
        return new RestTemplate(factory);
    }
}
