package cn.gsein.xuan.modules.system.user.controller;

import cn.gsein.xuan.modules.common.controller.BaseController;
import cn.gsein.xuan.modules.common.entity.JsonResult;
import cn.gsein.xuan.modules.common.exception.XuanException;
import cn.gsein.xuan.modules.system.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户相关的控制器
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/list")
    public JsonResult<List<User>> list() {
        throw new XuanException("测试一下");
    }
}
