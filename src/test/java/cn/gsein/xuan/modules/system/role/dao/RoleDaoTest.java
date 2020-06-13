package cn.gsein.xuan.modules.system.role.dao;

import cn.gsein.xuan.modules.system.permission.entity.Permission;
import cn.gsein.xuan.modules.system.role.entity.Role;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author G. Seinfeld
 * @since 2020/06/13
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    @Test
    void save() {
        Role role = new Role();
        role.setName("管理员");

        Permission permission = new Permission();
        permission.setId(2L);

        Permission permission1 = new Permission();
        permission1.setId(3L);


        List<Permission> permissions = Lists.newArrayList(permission, permission1);
        role.setPermissions(permissions);

        roleDao.save(role);
    }
}