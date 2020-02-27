package org.fe.ek.test.common.util.http;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @program: SimpleProj
 * @description: call remote service using http/https protocol
 * @author: Wang Zhenhua
 * @create: 2020-02-27
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-27
 **/
@Service
@EnableRetry
@Slf4j
public class RemoteCaller {

    @Autowired
    private CallService callServ;

    /**
     * call remote service with no additional header, no body
     * @param uri
     * @param method
     * @return
     */
    public String call(String uri, HttpMethod method) {
        return call(uri, method, null, null);
    }

    /**
     * call remote service with given additional headers and request body.
     * @param uri
     * @param method
     * @param headers
     * @param body
     * @return
     */
    public String call(String uri, HttpMethod method, Map<String, String> headers, String body) {
        try {
            return callServ.callretry(uri, method, headers, body);
        }catch (ResourceAccessException rex) {
            log.error("ResourceAccessException");
            rex.printStackTrace();
        }catch (UnsupportedEncodingException uex) {
            log.error("UnsupportedEncodingException");
            uex.printStackTrace();
        }
        throw new CmException(CmErrCode.E100000,"[RemoteCaller] error to call remote interface " + uri);
    }
}
