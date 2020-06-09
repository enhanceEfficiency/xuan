package cn.gsein.xuan.modules.system.user.controller;

import cn.gsein.xuan.modules.common.controller.BaseController;
import cn.gsein.xuan.modules.common.entity.JsonResult;
import cn.gsein.xuan.modules.common.exception.XuanException;
import cn.gsein.xuan.modules.system.user.entity.User;
import cn.gsein.xuan.modules.system.user.service.UserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public JsonResult<List<User>> list() {
        throw new XuanException("测试一下");
    }


    @PostMapping("/add")
    public JsonResult<Void> add(User user) {

        String salt = IdUtil.fastSimpleUUID();
        String password = new MD5(salt.getBytes(StandardCharsets.UTF_8)).digestHex(user.getPassword(), StandardCharsets.UTF_8);

        user.setPassword(password);
        user.setSalt(salt);
        userService.save(user);
        return JsonResult.ok();
    }

    @PostMapping("/login")
    public JsonResult<Void> login(String username, String password) {
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return JsonResult.ok();
    }
}
