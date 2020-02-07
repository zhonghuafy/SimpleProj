import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.model.util.CodecUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * @program: SimpleProj
 * @description: 大前置接口调用
 * @author: Wang Zhenhua
 * @create: 2020-02-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-06
 **/
@Slf4j
public class DqzInterfaceTest {

    @Test
    public void remoteif() {
        String baseUri = "http://waybill.yundasys.com:11020/waybill/api/json/v2/all";
        String sys = "V2JWA_YHHX";
        String apiKey = "3MHYpeYnkMg0y5TU";
        String shipId = "4303633426409";
        String data = CodecUtil.encodeHexString(apiKey,shipId);

        StringBuffer url = new StringBuffer(baseUri);
        url.append("?");
        url.append("sys=");
        url.append(sys);
        url.append("&data=");
        url.append(data);

        try {
            byte[] response;
//            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(null);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, byte[].class);
            response = responseEntity.getBody();
            String rawText = new String(response,"utf-8");
            log.info("[大前置接口调用] {}",rawText);

        }catch (ResourceAccessException rex) {
            log.error("ResourceAccessException");
            rex.printStackTrace();
        }catch (UnsupportedEncodingException uex) {
            log.error("UnsupportedEncodingException");
            uex.printStackTrace();
        }

    }
}
