package cn.gsein.xuan.modules.system.service.impl;

import cn.gsein.xuan.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.core.util.Constant;
import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.dao.MenuDao;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.entity.User;
import cn.gsein.xuan.modules.system.service.MenuService;
import cn.hutool.core.lang.tree.Tree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单Service层实现类
 *
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDao> implements MenuService {


    @Override
    @Transactional(readOnly = true, noRollbackFor = Exception.class)
    public Optional<Menu> findById(Long id) {
        return dao.findById(id);
    }


    @Transactional(readOnly = true, noRollbackFor = Exception.class)
    public List<Tree<Optional<Menu>>> getMenuTree() {
        List<Menu> menus = new ArrayList<>();
        Optional<User> loginUser = ShiroUtil.getLoginUser();
        if (loginUser.isPresent() && Constant.ADMINISTRATOR_ID.equals(loginUser.get().getId())) {
            menus = dao.findAll();
        } else {
            Optional<List<Role>> roles = loginUser.map(User::getRoles);
            Optional<Set<Permission>> permissions = roles.map(
                    roleList -> roleList.stream()
                            .map(Role::getPermissions)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toSet())
            );

        }
        return null;

    }
}
