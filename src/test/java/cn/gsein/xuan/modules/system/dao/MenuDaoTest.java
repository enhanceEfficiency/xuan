package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.Menu;
import cn.gsein.xuan.modules.system.entity.Permission;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author G. Seinfeld
 * @since 2020/06/19
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class MenuDaoTest {

    @Resource
    MenuDao menuDao;

    @Test
    void findPermissions() {

        List<Long> permissionIds = Arrays.asList(2L, 9L);
        Set<Permission> permissions = permissionIds.stream().map(id -> {
            Permission permission = new Permission();
            permission.setId(id);
            return permission;
        }).collect(Collectors.toSet());
        List<Menu> menus = menuDao.findByPermissions(permissions);
        Assertions.assertThat(menus).hasSize(3);
        System.out.println(menus);
    }
}