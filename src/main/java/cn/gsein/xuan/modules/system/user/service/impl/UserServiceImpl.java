package cn.gsein.xuan.modules.system.user.service.impl;

import cn.gsein.xuan.modules.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.modules.system.user.dao.UserDao;
import cn.gsein.xuan.modules.system.user.entity.User;
import cn.gsein.xuan.modules.system.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDao> implements UserService {
}
