package cn.gsein.xuan.modules.system.user.controller;

import cn.gsein.xuan.common.controller.BaseController;
import cn.gsein.xuan.common.entity.JsonResult;
import cn.gsein.xuan.common.entity.ResultCode;
import cn.gsein.xuan.common.exception.XuanException;
import cn.gsein.xuan.core.security.TokenService;
import cn.gsein.xuan.modules.system.user.entity.User;
import cn.gsein.xuan.modules.system.user.service.UserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

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

    @Resource
    private TokenService tokenService;

    @GetMapping("/list")
    public JsonResult<List<User>> list() {
        throw new XuanException("测试一下");
    }


    /**
     * 添加新用户
     */
    @PostMapping("/add")
    public JsonResult<Void> add(User user) {

        String salt = IdUtil.fastSimpleUUID();
        String password = new MD5(salt.getBytes(StandardCharsets.UTF_8)).digestHex(user.getPassword(), StandardCharsets.UTF_8);

        user.setPassword(password);
        user.setSalt(salt);
        userService.save(user);
        return JsonResult.ok();
    }

    /**
     * 执行登陆，获取token
     *
     * @param username 用户名
     * @param password 密码
     * @return 封装的token
     */
    @PostMapping("/login")
    public JsonResult<String> login(String username, String password) {
        Optional<User> user = userService.getUserByUsernameAndDeletedIsFalse(username);
        // 用户不存在
        if (!user.isPresent()) {
            return JsonResult.get(ResultCode.BAD_REQUEST, "用户名或密码错误", null);
        }
        String savedPassword = user.get().getPassword();
        String savedSalt = user.get().getSalt();

        MD5 md5 = new MD5(savedSalt.getBytes(StandardCharsets.UTF_8));
        String encryptedPassword = md5.digestHex(password, StandardCharsets.UTF_8);
        // 密码不正确
        if (!savedPassword.equals(encryptedPassword)) {
            return JsonResult.get(ResultCode.BAD_REQUEST, "用户名或密码错误", null);
        }

        // 生成token
        String jwt = tokenService.generateToken(username);
        return JsonResult.ok(jwt);

    }
}
