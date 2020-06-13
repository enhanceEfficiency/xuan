package cn.gsein.xuan.modules.system.user.dao;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.modules.system.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Repository
public interface UserDao extends BaseDao<User> {

    Optional<User> getUserByUsernameAndDeletedIsFalse(String username);
}
