package org.fe.ek.test.model.constant;

/**
 * @program: SimpleProj
 * @description: SysConst
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
public interface SysConst {
    /**
     * 请求头中token名
     */
    String HEADER_TOKEN_NAME = "AuthToken";

    /**
     * 分页，当前页码
     */
    String PAGE_CURRENT = "current";

    /**
     * 分页，每页记录数
     */
    String PAGE_SIZE = "size";

    int DEFAULT_PAGE_NUM = 1;

    int DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页时最大记录数
     */
    int MAX_PAGE_SIZE = 100;

    int REDIS_NEVER_EXPIRE = 0;

    /**
     * 失败重试次数
     */
    int FAIL_RETRY = 3;

    /**
     * token在redis中存储时间（秒），暂定25小时
     */
    int REDIS_TOKEN_EXPIRE = 60 * 60 * 25;

    /**
     * token过期时间（秒），暂定24小时
     */
    long TOKEN_EXPIRE = 60 * 60 * 24;
}
