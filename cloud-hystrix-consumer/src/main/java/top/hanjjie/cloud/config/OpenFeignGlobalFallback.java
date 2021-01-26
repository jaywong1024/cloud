package top.hanjjie.cloud.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.hanjjie.cloud.utils.ResultBean;

/**
 * OpenFeign 全局服务降级处理
 */
@Slf4j
@Component
public class OpenFeignGlobalFallback {

    private static final String TIMEOUT = "请求资源超时...触发服务降级，提示用户稍后再试";
    private static final String UNKNOWN = "发生未知异常...触发服务降级，联系后端仔快点改";

    public <T> ResultBean<T> fallback(Throwable throwable) {
        ResultBean<T> resultBean = new ResultBean<>();
        if (throwable instanceof HystrixTimeoutException) {
            resultBean.setCode(ResultBean.TIMEOUT);
            resultBean.setMsg(TIMEOUT);
        } else {
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg(UNKNOWN);
        }
        log.error(resultBean.getMsg());
        return resultBean;
    }

}
