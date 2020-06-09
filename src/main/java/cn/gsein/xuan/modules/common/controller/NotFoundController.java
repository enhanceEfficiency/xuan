package cn.gsein.xuan.modules.common.controller;

import cn.gsein.xuan.modules.common.entity.JsonResult;
import cn.gsein.xuan.modules.common.entity.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理/error请求，以捕获404错误
 * 这里必须要实现ErrorController接口，不然Spring Boot会认为/error的映射是重复的（与Spring Boot默认实现的BaseErrorController重复）
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
@RestController
public class NotFoundController implements ErrorController {


    @RequestMapping("/error")
    public JsonResult<Object> error() {
        return JsonResult.get(ResultCode.NOT_FOUND, null);
    }

    /**
     * 这个方法没有实际的作用，只是实现接口的副产品，接口中该方法已经标识为deprecated
     */
    @Override
    public String getErrorPath() {
        return null;
    }
}
