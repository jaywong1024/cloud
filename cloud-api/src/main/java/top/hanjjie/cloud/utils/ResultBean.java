package top.hanjjie.cloud.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用请求返回
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 成功、不允许、失败
     */
    public static final int SUCCESS = 200;
    public static final int NO_PERMISSION = 403;
    public static final int TIMEOUT = 408;
    public static final int FAIL = 500;

    private int code = SUCCESS;
    private String msg = "success";
    private T data;

    public ResultBean(T data) {
        super();
        setData(data);
    }

    public ResultBean(String errorMsg) {
        super();
        setMsg(errorMsg);
        setCode(NO_PERMISSION);
    }

    public ResultBean(Throwable cause) {
        super();
        setMsg(cause.toString());
        setCode(FAIL);
    }

}
