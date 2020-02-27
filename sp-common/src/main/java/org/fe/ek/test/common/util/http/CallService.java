package org.fe.ek.test.common.util.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @program: SimpleProj
 * @description: call remote service, will auto retry when fail
 * @author: Wang Zhenhua
 * @create: 2020-02-27
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-27
 **/
@Service
@Slf4j
class CallService {

    @Retryable(value = ResourceAccessException.class, backoff = @Backoff(delay = 2000))
    public String callretry(String uri, HttpMethod method, Map<String, String> headers, String body) throws UnsupportedEncodingException {
        log.info("[CallService] calling remote url: {} with HttpMethod {}", uri, method);
        byte[] response;
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((k,v) -> {
                httpHeaders.add(k,v);
            });
        }
        HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(uri, method, httpEntity, byte[].class);
        response = responseEntity.getBody();
        String rawText = new String(response,"utf-8");
        log.info("[接口调用] {}",rawText);
        return rawText;
    }
}
