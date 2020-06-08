package cn.gsein.xuan.modules.common.entity;

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
    OK(200, "操作成功！"),
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
