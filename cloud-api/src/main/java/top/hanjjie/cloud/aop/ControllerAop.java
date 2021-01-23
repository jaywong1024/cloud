package top.hanjjie.cloud.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.utils.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ControllerAop {

    private static final String PARAMS_MSG = "请求参数错误...触发全局异常处理，联系前端仔改一下参数";
    private static final String PARAMS_ERROR = "请求参数错误, url: {}, msg: {}";

    /**
     * 处理参数异常
     */
    @ExceptionHandler({ParamsException.class})
    public ResultBean<?> handelParamException(HttpServletRequest request, ParamsException ex) {
        ResultBean<String> resultBean = new ResultBean<>();
        resultBean.setMsg(PARAMS_MSG);
        resultBean.setData(ex.getMessage());
        log.error(PARAMS_ERROR, request.getRequestURL(), ex.getMessage());
        return resultBean;
    }

}
