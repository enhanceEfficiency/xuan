package cn.gsein.xuan.modules.common.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 后台返回给前端的json对象
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Data
@Builder
public class JsonResult<T> {
    private final int code;

    private final String message;

    private final T data;

    private JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private JsonResult(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(ResultCode.OK, data);
    }

    public static <T> JsonResult<T> ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> error(T data) {
        return new JsonResult<>(ResultCode.ERROR, data);
    }

    public static <T> JsonResult<T> error(String message, T data) {
        return new JsonResult<>(ResultCode.ERROR.getCode(), message, data);
    }

    public static <T> JsonResult<T> error(String message) {
        return error(message, null);
    }

    public static <T> JsonResult<T> error() {
        return error(null);
    }

    public static <T> JsonResult<T> get(ResultCode resultCode, T data) {
        return new JsonResult<>(resultCode, data);
    }

    public static <T> JsonResult<T> get(int code, String message, T data) {
        return new JsonResult<>(code, message, data);
    }
}
