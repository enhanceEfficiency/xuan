package cn.gsein.xuan.common.controller;

import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Controller
@Slf4j
public class BaseController {

    /**
     * 当前登录用户名
     */
    protected Optional<String> loginUserName() {
        Optional<User> loginUser = ShiroUtil.getLoginUser();
        return loginUser.map(User::getUsername);
    }

    /**
     * 当前登录用户
     */
    protected Optional<User> loginUser() {
        return ShiroUtil.getLoginUser();
    }
}
