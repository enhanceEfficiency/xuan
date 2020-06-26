package cn.gsein.xuan.modules.system.service.impl;

import cn.gsein.xuan.common.service.impl.BaseServiceImpl;
import cn.gsein.xuan.core.util.Constant;
import cn.gsein.xuan.core.util.ShiroUtil;
import cn.gsein.xuan.modules.system.dao.MenuDao;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.entity.User;
import cn.gsein.xuan.modules.system.enums.MenuType;
import cn.gsein.xuan.modules.system.service.MenuService;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
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


    @Override
    @Transactional(readOnly = true, noRollbackFor = Exception.class)
    public List<Tree<Optional<Menu>>> getMenuTreeForLoginUser() {
        List<Menu> menus;
        Optional<User> loginUser = ShiroUtil.getLoginUser();

        // 管理员直接返回所有Menu
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
            Optional<List<Menu>> optionalMenus = permissions.map(perms -> dao.findByPermissions(perms));
            menus = optionalMenus.orElse(new ArrayList<>());
        }

        // 只保留类型为菜单的Menu
        menus = menus.stream().filter(menu -> MenuType.MENU.equals(menu.getType())).collect(Collectors.toList());

        // 将菜单做树型化处理
        List<TreeNode<Optional<Menu>>> menuTreeNodes = menus.stream()
                .map(menu -> {
                    Optional<Menu> optional = Optional.of(menu);
                    return new TreeNode<>(optional, optional.map(Menu::getParent), optional.map(Menu::getName).orElse(""), 1);
                }).collect(Collectors.toList());
        return TreeUtil.build(menuTreeNodes, Optional.empty());

    }
}
