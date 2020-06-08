package cn.gsein.xuan.modules.system.user.dao;

import cn.gsein.xuan.modules.system.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

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
        dao.save(user);
    }
}