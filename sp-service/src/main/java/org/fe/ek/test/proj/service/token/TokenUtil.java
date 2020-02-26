package org.fe.ek.test.proj.service.token;

import com.alibaba.fastjson.JSONObject;
import org.fe.ek.test.common.util.SHA256;
import org.fe.ek.test.model.constant.SysConst;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * @program: SimpleProj
 * @description: token tool, generate/check token
 * @author: Wang Zhenhua
 * @create: 2019-10-24
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-10-24
 **/
public final class TokenUtil {

    private final static int TOKEN_HEADER_INDEX = 0;

    private final static int TOKEN_PAYLOAD_INDEX = 1;

    private final static int TOKEN_SIGN_INDEX = 2;

    private final static int TOKEN_SECTION_NUM = 3;

    private final static String TOKEN_SECTION_SYMBOL = ".";

    private TokenUtil(){}

    /**
     * 获取新token
     * @param userName
     * @return
     */
    public static String newToken(@NotNull String userName) {
        StringBuilder token = new StringBuilder();
        TokenPayload payload = new TokenPayload();
        payload.setUserName(userName);
        payload.setIat(Instant.now().toEpochMilli());
        LocalDateTime nowTime = LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
        nowTime = nowTime.plusSeconds(SysConst.TOKEN_EXPIRE);
        Instant exp = nowTime.atZone(ZoneId.systemDefault()).toInstant();
        payload.setExp(exp.toEpochMilli());
        String headerStr = JSONObject.toJSONString(new TokenHeader());
        headerStr = Base64.getEncoder().encodeToString(headerStr.getBytes());
        token.append(headerStr);
        String payloadStr = JSONObject.toJSONString(payload);
        payloadStr = Base64.getEncoder().encodeToString(payloadStr.getBytes());
        token.append(TOKEN_SECTION_SYMBOL);
        token.append(payloadStr);
        String sign = SHA256.encrypt(token.toString());
        token.append(TOKEN_SECTION_SYMBOL);
        token.append(sign);
        return token.toString();
    }

    /**
     * 校验token是否有效
     * @param token
     * @return
     */
    public static boolean isValid(@NotNull String token) {
        String[] tokenParts = token.split("\\.");
        if (tokenParts == null || tokenParts.length != TOKEN_SECTION_NUM) {
            return false;
        }
        return isValidPayload(tokenParts[1]) && isValidSign(tokenParts);
    }

    /**
     * 校验token payload部分
     * 校验核发时间、过期时间
     * @param payload
     * @return
     */
    private static boolean isValidPayload(String payload) {
        boolean isValid = true;
        try {
            String payloadStr = new String(Base64.getDecoder().decode(payload));
            TokenPayload tokenPayload = JSONObject.parseObject(payloadStr,TokenPayload.class);
            Instant iat = Instant.ofEpochMilli(tokenPayload.getIat());
            Instant exp = Instant.ofEpochMilli(tokenPayload.getExp());
            Instant now = Instant.now();
            if (exp.isBefore(now)) {
                isValid = false;
            }
            if (iat.isAfter(now)) {
                isValid = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }

    /**
     * 校验签名是否有效
     * @param tokenParts
     * @return
     */
    private static boolean isValidSign(String[] tokenParts) {
        StringBuilder tokenBuilder = new StringBuilder();
        String headerStr = JSONObject.toJSONString(new TokenHeader());
        headerStr = Base64.getEncoder().encodeToString(headerStr.getBytes());
        if (!headerStr.equals(tokenParts[TOKEN_HEADER_INDEX])) {
            return false;
        }
        tokenBuilder.append(headerStr);
        tokenBuilder.append(TOKEN_SECTION_SYMBOL);
        tokenBuilder.append(tokenParts[TOKEN_PAYLOAD_INDEX]);
        String sign = SHA256.encrypt(tokenBuilder.toString());
        return sign.equals(tokenParts[TOKEN_SIGN_INDEX]);
    }
}
