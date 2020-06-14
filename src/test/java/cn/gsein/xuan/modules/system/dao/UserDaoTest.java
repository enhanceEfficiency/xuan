package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.Role;
import cn.gsein.xuan.modules.system.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserDaoTest {

    @Resource
    UserDao dao;

    @Test
    void insert() {
        User user = new User();
        user.setUsername("lhing17");
        user.setPassword("123");
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        roles.add(role);

        Role role1 = new Role();
        role.setId(2L);
        roles.add(role1);
        user.setRoles(roles);


        dao.save(user);
    }
}