package org.fe.ek.test.common.exp;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.po.ResultPO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;

/**
 * @program: SimpleProj
 * @description: GlobalExceptionHandler
 * @author: Wang Zhenhua
 * @create: 2020-02-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-07
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CmException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultPO recipeException(CmException e) {
        log.error("系统异常: {}", e);
        return new ResultPO(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultPO methodArgumentException(MethodArgumentNotValidException e) {
        log.error("请求参数验证异常:", e);
        StringBuffer eCVBuilder = new StringBuffer();
        e.getBindingResult().getFieldErrors().forEach(a -> eCVBuilder.append(a.getDefaultMessage()).append(";"));
        eCVBuilder.deleteCharAt(eCVBuilder.length() - 1);
        String message = eCVBuilder.toString();
        return new ResultPO(CmErrCode.E100001.getCode(), CmErrCode.E100001.getMessage() + "：" + message, null);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultPO bindingException(BindException e) {
        log.error("请求参数验证异常:", e);
        return new ResultPO(CmErrCode.E100001.getCode(), CmErrCode.E100001.getMessage() + "：" + e.getBindingResult().getFieldError().getDefaultMessage(), null);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultPO paramValidException(ConstraintViolationException e) {
        log.error("请求参数验证异常:", e);
        StringBuffer eCVBuilder = new StringBuffer();
        e.getConstraintViolations().forEach(a -> eCVBuilder.append(a.getMessage() + ";"));
        eCVBuilder.deleteCharAt(eCVBuilder.length() - 1);
        String message = eCVBuilder.toString();
        return new ResultPO(CmErrCode.E100001.getCode(), CmErrCode.E100001.getMessage() + "：" + message, null);
    }

    /**
     * 上传文件大小超过限制异常
     *
     */
    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultPO fileExceedsSize(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ResultPO(CmErrCode.E100000.getCode(), "上传的文件大小超过限制", null);
    }
    /**
     * 拦截未知的运行时异常
     *
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultPO notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ResultPO(CmErrCode.E100000.getCode(), CmErrCode.E100000.getMessage(), null);
    }
}
