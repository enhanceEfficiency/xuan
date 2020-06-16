package cn.gsein.xuan.common.entity;

/**
 * 返回码枚举
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public enum ResultCode {
    /**
     * 请求成功
     */
    OK(20000, "操作成功！"),
    /**
     * 资源未找到
     */
    NOT_FOUND(404, "未找到合适的处理器"),
    /**
     * 请求未授权
     */
    UNAUTHORIZED(403, "请求未授权"),
    /**
     * 客户端请求不能被服务器理解，业务异常
     */
    BAD_REQUEST(400, "请求错误"),
    /**
     * 服务器异常
     */
    ERROR(500, "服务器异常");
    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
