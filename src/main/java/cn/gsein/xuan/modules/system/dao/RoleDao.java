package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.common.dao.BaseDao;
import cn.gsein.xuan.modules.system.entity.Role;

import java.util.Optional;

/**
 * 角色的持久层
 *
 * @author G. Seinfeld
 * @since 2020/06/13
 */
public interface RoleDao extends BaseDao<Role> {

    Optional<Role> findByNameAndDeletedIsFalse(String name);
}
