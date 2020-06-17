package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

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
        role.setName("super_admin");
        role.setDescription("超级管理员");

        roleDao.save(role);
    }
}