package org.fe.ek.test.common.util;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @program: SimpleProj
 * @description: call remote interfaces or urls using http/https protocol
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
@Slf4j
public class RemoteCaller {

    private RemoteCaller() {}

    public static String call(String uri, HttpMethod method) {
        return call(uri, method, null, null);
    }

    public static String call(String uri, HttpMethod method, Map<String, String> headers, String body) {
        int i = 0;
        do {
            try {
                return callinterface(uri, method, headers, body);
            }catch (ResourceAccessException rex) {
                log.error("ResourceAccessException");
                rex.printStackTrace();
            }catch (UnsupportedEncodingException uex) {
                log.error("UnsupportedEncodingException");
                uex.printStackTrace();
            }
            sleep();
        } while (++i < 3);
        throw new CmException(CmErrCode.E100000,"[RemoteCaller] error to call remote interface " + uri);
    }

    private static String callinterface(String uri, HttpMethod method, Map<String, String> headers, String body) throws ResourceAccessException, UnsupportedEncodingException {
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

    private static void sleep() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException iex) {}
    }
}
