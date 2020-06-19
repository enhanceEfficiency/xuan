package cn.gsein.xuan.modules.system.service.impl;

import cn.gsein.xuan.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.core.util.Constant;
import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.dao.PermissionDao;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.entity.User;
import cn.gsein.xuan.modules.system.service.PermissionService;
import cn.hutool.core.lang.tree.Tree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/14
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionDao> implements PermissionService {

}
