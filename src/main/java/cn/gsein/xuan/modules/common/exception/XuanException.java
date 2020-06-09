package cn.gsein.xuan.modules.common.exception;

/**
 * 自定义异常的基类
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
public class XuanException extends RuntimeException {

    public XuanException(String message) {
        super(message);
    }

    public XuanException(String message, Throwable cause) {
        super(message, cause);
    }

}
