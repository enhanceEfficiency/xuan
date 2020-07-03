package cn.gsein.xuan.modules.system.service.impl;

import cn.gsein.xuan.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.modules.system.dao.RoleDao;
import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色相关业务层实现类
 *
 * @author G. Seinfeld
 * @since 2020/07/03
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDao> implements RoleService {
}
