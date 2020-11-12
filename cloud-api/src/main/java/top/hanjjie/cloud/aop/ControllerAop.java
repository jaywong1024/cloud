package top.hanjjie.cloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hanjjie.cloud.exception.ParamException;
import top.hanjjie.cloud.utils.ResultBean;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ControllerAop {

    private static final String PARAM_ERROR = "param error, url: {}, msg: {}";

    @ExceptionHandler({ParamException.class})
    public ResultBean<Object> handelException(HttpServletRequest request, ParamException ex) {
        log.warn(PARAM_ERROR, request.getRequestURL(), ex.getMessage());
        return new ResultBean<>(ex.getMessage());
    }

}
