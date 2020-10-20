package top.hanjjie.cloud.utils;

/**
 * 通用请求返回
 */
public class HttpResponse {

    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public HttpResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public HttpResponse setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 请求成功
     */
    public static HttpResponse success() {
        return new HttpResponse().setCode(200).setMsg("request success");
    }

    /**
     * 参数异常
     */
    public static HttpResponse paramsError() {
        return new HttpResponse().setCode(403).setMsg("request params error");
    }

    /**
     * 服务器内部错误
     */
    public static HttpResponse serverError() {
        return new HttpResponse().setCode(500).setMsg("server error");
    }

}
