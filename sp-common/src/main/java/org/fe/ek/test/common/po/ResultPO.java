package org.fe.ek.test.common.po;

import org.fe.ek.test.common.exp.CmErrCode;

/**
 * @program: SimpleProj
 * @description: ResultPO
 * @author: Wang Zhenhua
 * @create: 2020-02-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-07
 **/
public class ResultPO {
    private String code;

    private String message;

    private Object data;

    private ResultPO(){}

    public ResultPO(CmErrCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ResultPO(CmErrCode errorCode, String msg) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage() + ": " + msg;
    }

    public ResultPO(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResultPO(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultPO success() {
        ResultPO resultPO = new ResultPO();
        resultPO.setCode(CmErrCode.E0.getCode());
        resultPO.setMessage(CmErrCode.E0.getMessage());
        return resultPO;
    }

    public static ResultPO success(Object data) {
        ResultPO resultPO = new ResultPO();
        resultPO.setCode(CmErrCode.E0.getCode());
        resultPO.setMessage(CmErrCode.E0.getMessage());
        resultPO.setData(data);
        return resultPO;
    }

    public void setErrorMsg(CmErrCode errorCode) {
        setErrorMsg(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 将定义的错误信息追加到错误码中的错误信息
     * @param errorCode
     * @param appendMsg
     */
    public void setErrorMsgAppend(CmErrCode errorCode, String appendMsg) {
        String msg = errorCode.getMessage() + "：" + appendMsg;
        setErrorMsg(errorCode.getCode(), msg);
    }

    public void setErrorMsg(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean issuccess() {
        if (CmErrCode.E0.getCode().equals(this.getCode())) {
            return true;
        }
        return false;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
