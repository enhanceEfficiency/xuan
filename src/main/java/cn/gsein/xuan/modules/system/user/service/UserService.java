package cn.gsein.xuan.modules.system.user.service;

import cn.gsein.xuan.modules.common.service.BaseService;
import cn.gsein.xuan.modules.system.user.dao.UserDao;
import cn.gsein.xuan.modules.system.user.entity.User;

import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
public interface UserService extends BaseService<User, UserDao> {

    Optional<User> getUserByUsernameAndDeletedIsFalse(String username);
}
