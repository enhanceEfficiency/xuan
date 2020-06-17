package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserDaoTest {

    @Resource
    UserDao dao;

    @Resource
    RoleDao roleDao;

    @Test
    void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("111111");

        dao.save(user);
    }

    @Test
    void update() {
        Optional<User> admin = dao.findByUsernameAndDeletedIsFalse("admin");
        Optional<Role> adminRole = roleDao.findByNameAndDeletedIsFalse("admin");
        Optional<Role> superAdminRole = roleDao.findByNameAndDeletedIsFalse("super_admin");
        admin.ifPresent(
                a -> {
                    List<Role> roles = new ArrayList<>();
                    adminRole.ifPresent(roles::add);
                    superAdminRole.ifPresent(roles::add);
                    if (!CollectionUtils.isEmpty(roles)) {
                        a.setRoles(roles);
                        dao.save(a);
                    }
                }
        );


    }
}