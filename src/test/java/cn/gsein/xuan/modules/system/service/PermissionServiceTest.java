package cn.gsein.xuan.modules.system.service;

import cn.gsein.xuan.modules.system.entity.Permission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PermissionServiceTest {

    @Resource
    PermissionService permissionService;

    @Test
    void save() {
        Permission permission = new Permission();
        permission.setName("sys:user:remove");
        Permission saved = permissionService.save(permission);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(saved.getName(), permission.getName());
    }
}