package cn.gsein.xuan.modules.system.dao;

import cn.gsein.xuan.modules.system.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

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
        user.setUsername("admin");
        user.setPassword("111111");

        dao.save(user);
    }
}