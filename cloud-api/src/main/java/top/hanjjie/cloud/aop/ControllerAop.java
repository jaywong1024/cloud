package top.hanjjie.cloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.exception.TimeoutException;
import top.hanjjie.cloud.utils.ResultBean;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ControllerAop {

    private static final String PARAMS_ERROR = "params error, url: {}, msg: {}";
    private static final String TIMEOUT_ERROR = "request timeout, url: {}, msg: {}";

    @ExceptionHandler({ParamsException.class})
    public ResultBean<Object> handelParamException(HttpServletRequest request, ParamsException ex) {
        log.warn(PARAMS_ERROR, request.getRequestURL(), ex.getMessage());
        return new ResultBean<>(ex.getMessage());
    }

    @ExceptionHandler({TimeoutException.class})
    public ResultBean<Object> handelTimeoutException(HttpServletRequest request, ParamsException ex) {
        log.warn(TIMEOUT_ERROR, request.getRequestURL(), ex.getMessage());
        ResultBean<Object> timeoutResultBean = new ResultBean<>();
        timeoutResultBean.setCode(ResultBean.TIMEOUT);
        timeoutResultBean.setMsg(ex.getMessage());
        return timeoutResultBean;
    }

}
