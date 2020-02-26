package org.fe.ek.test.common.exp;

/**
 * @program: TestProj
 * @description: common exception
 * @author: Wang Zhenhua
 * @create: 2020-01-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-21
 **/
public class CmException extends RuntimeException {

    private String code;
    private String message;

    public CmException() {
        this.code = CmErrCode.E100000.getCode();
        this.message = CmErrCode.E100000.getMessage();
    }

    public CmException(CmErrCode errCode) {
        this.code = errCode.getCode();
        this.message = errCode.getMessage();
    }

    public CmException(CmErrCode errCode, String msg) {
        this.code = errCode.getCode();
        this.message = errCode.getMessage() + ": " + msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
