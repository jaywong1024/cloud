package top.hanjjie.cloud.config;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.hanjjie.cloud.utils.ResultBean;
import java.net.SocketTimeoutException;

/**
 * OpenFeign 通用服务降级方法
 */
@Slf4j
@Component
public class OpenFeignFallbackFactory implements FallbackFactory<ResultBean<?>> {

    private static final String TIMEOUT = "请求资源超时...触发服务降级，提示用户稍后再试";
    private static final String UNKNOWN = "发生未知异常...触发服务降级，联系后端仔快点改";

    @Override
    public ResultBean<?> create(Throwable throwable) {
        ResultBean<Object> resultBean = new ResultBean<>();
        if (throwable instanceof SocketTimeoutException) {
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
