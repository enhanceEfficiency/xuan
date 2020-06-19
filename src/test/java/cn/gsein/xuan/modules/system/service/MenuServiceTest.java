package cn.gsein.xuan.modules.system.service;

import cn.gsein.xuan.common.util.DataAccessHelper;
import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import cn.gsein.xuan.modules.system.enums.MenuType;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MenuServiceTest {

    @Resource
    MenuService menuService;

    @Resource
    PermissionService permissionService;

    @Autowired
    DataAccessHelper dataAccessHelper;

    @Test
    void save() {
        List<Long> ids = Arrays.asList(2L, 3L, 9L, 10L);
        List<Permission> permissions = permissionService.findAllById(ids);

//        Menu menu = new Menu();
//        menu.setName("系统管理");
//        menu.setType(MenuType.MENU);
//        menu.setIcon("sys");
//        menu.setAncestors("#");
//        menuService.save(menu);
//
//        Menu subMenu = new Menu();
//        subMenu.setName("用户管理");
//        subMenu.setParent(menu);
//        subMenu.setUrl("/user/list");
//        subMenu.setIcon("user");
//        subMenu.setType(MenuType.MENU);
//        subMenu.setPermission(permissions.get(2));
//        subMenu.setAncestors(menu.getAncestors() + "," + menu.getId());

        Optional<Menu> parent = menuService.findById(15L);

        Menu button = new Menu();
        button.setName("增加");
        button.setParent(parent.orElse(null));
        button.setIcon("add");
        button.setType(MenuType.BUTTON);
        button.setPermission(permissions.get(0));
        button.setAncestors(parent.map(menu -> menu.getAncestors() + "," + menu.getId()).orElse("#"));

        menuService.save(button);
    }

    @Test
    void treeify() {
        List<Menu> menus = menuService.findAll();
        List<TreeNode<Optional<Menu>>> menuTreeNodes = menus.stream()
                .map(menu -> {
                    Optional<Menu> optional = Optional.of(menu);
                    return new TreeNode<>(optional, optional.map(Menu::getParent), optional.map(Menu::getName).orElse(""), 1);
                })
                .collect(Collectors.toList());
        List<Tree<Optional<Menu>>> treeList = TreeUtil.build(menuTreeNodes, Optional.empty());
        System.out.println(treeList);
    }
}