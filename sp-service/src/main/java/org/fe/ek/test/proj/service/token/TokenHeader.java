package org.fe.ek.test.proj.service.token;

import lombok.Data;

/**
 * @program: SimpleProj
 * @description: TokenHeader
 * @author: Wang Zhenhua
 * @create: 2019-10-24
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2019-10-24
 **/
@Data
public class TokenHeader {

    private final String typ = "JWT";
    private final String alg = "HS256";
}
