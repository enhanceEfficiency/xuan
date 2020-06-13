package cn.gsein.xuan.modules.system.user.service;

import cn.gsein.xuan.common.service.BaseService;
import cn.gsein.xuan.modules.system.user.entity.User;

import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface UserService extends BaseService<User> {

    Optional<User> getUserByUsernameAndDeletedIsFalse(String username);
}
