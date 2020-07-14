package cn.gsein.xuan.modules.system.controller;

import cn.gsein.xuan.common.controller.BaseController;
import cn.gsein.xuan.common.entity.JsonResult;
import cn.gsein.xuan.common.entity.ResultCode;
import cn.gsein.xuan.common.exception.XuanException;
import cn.gsein.xuan.core.security.TokenService;
import cn.gsein.xuan.modules.system.entity.User;
import cn.gsein.xuan.modules.system.service.UserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/sys/user")
@Slf4j
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
     * @return 封装的token
     */
    @PostMapping("/login")
    public JsonResult<String> login(User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        Optional<User> user = userService.findByUsernameAndDeletedIsFalse(username);
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


    @GetMapping("/info")
    public JsonResult<User> info(String token) {

        log.info("当前登录用户为：" + loginUserName().orElse(null));

        // token格式Bearer XXX
        if (!StringUtils.isEmpty(token) && token.startsWith("Bearer")) {
            token = token.substring(7);
        }

        // 解析JWT token
        DecodedJWT decodedJWT = tokenService.decode(token);
        String username = decodedJWT.getClaim("username").asString();
        Optional<User> loginUser = userService.findByUsernameAndDeletedIsFalse(username);

        // 传头像、介绍、角色和名字
        return loginUser.map(JsonResult::ok).orElseGet(() -> JsonResult.error("用户不存在"));

    }
}
