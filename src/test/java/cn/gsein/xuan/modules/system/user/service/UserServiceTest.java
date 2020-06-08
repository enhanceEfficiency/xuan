package cn.gsein.xuan.modules.system.user.service;

import cn.gsein.xuan.modules.system.user.entity.User;
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
class UserServiceTest {

    @Resource
    UserService service;

    @Test
    void save() {
        User user = new User();
        user.setUsername("456");
        user.setPassword("789");


        service.save(user);

    }
}