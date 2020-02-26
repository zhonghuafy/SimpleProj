package org.fe.ek.test.proj.service.token;

import lombok.Data;

/**
 * @program: SimpleProj
 * @description: TokenPayload
 * @author: Wang Zhenhua
 * @create: 2019-10-24
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-10-24
 **/
@Data
public class TokenPayload {
    private final String iss = "ORG_FE_EK";
    private long iat;
    private long exp;
    private String userName;
}
