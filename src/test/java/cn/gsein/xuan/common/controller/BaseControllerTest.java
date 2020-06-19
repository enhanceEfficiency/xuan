package cn.gsein.xuan.common.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BaseControllerTest {

    @Resource
    BaseController baseController;

    @Test
    void loginUserName() {
        Optional<String> loginUserName = baseController.loginUserName();
        System.out.println(loginUserName.orElse(null));
    }
}