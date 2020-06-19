package cn.gsein.xuan.core.util;

import cn.gsein.xuan.modules.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@Slf4j
public final class ShiroUtil {
    private ShiroUtil() {
    }

    public static Optional<User> getLoginUser() {
        try {
            Subject subject = SecurityUtils.getSubject();
            return Optional.ofNullable((User) subject.getPrincipal());
        } catch (Exception e) {
            log.warn("当前没有登录用户", e);
            return Optional.empty();
        }
    }
}
