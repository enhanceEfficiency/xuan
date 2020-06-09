package cn.gsein.xuan.modules.common.controller;

import cn.gsein.xuan.modules.common.entity.JsonResult;
import cn.gsein.xuan.modules.common.exception.XuanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理Controller中抛出的异常
     *
     * @param e 异常
     * @return 返回给前端的json结果
     */
    @ExceptionHandler(XuanException.class)
    public JsonResult<Object> xuanError(XuanException e) {
        log.warn(e.getMessage(), e);
        return JsonResult.error("这是一个Xuan异常" + e.getMessage());
    }

    /**
     * 处理Controller中抛出的异常
     *
     * @param e 异常
     * @return 返回给前端的json结果
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<Object> error(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.error(e.getMessage());
    }


}
