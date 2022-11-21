package org.fe.ek.test.common.exp;

/**
 * @program: SimpleProj
 * @description: common error code
 * @author: Wang Zhenhua
 * @create: 2020-02-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-07
 **/
public enum CmErrCode {

    /**
     * 成功
     */
    E0("0","成功"),

    /**
     * 系统异常，请稍后再试
     */
    E100000("100000", "系统异常，请稍后再试"),
    /**
     * 请求参数异常
     */
    E100001("100001", "请求参数异常"),
    /**
     * 根据请求参数获取不到数据
     */
    E100002("100002", "根据请求参数获取不到数据"),
    /**
     * 不符合业务逻辑
     */
    E100003("100003", "不符合业务逻辑"),
    /**
     * 无效的状态
     */
    E100004("100004", "无效的状态"),
    /**
     * 网络出错
     */
    E100005("100005", "网络出错"),
    /**
     * 服务限流
     */
    E100006("100006", "服务限流，请稍后重试"),
    /**
     * 数据重复
     */
    E200001("200001","数据重复")
    ;
    CmErrCode(String code,String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
