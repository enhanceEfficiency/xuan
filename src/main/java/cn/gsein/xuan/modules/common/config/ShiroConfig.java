package cn.gsein.xuan.modules.common.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro安全框架相关的配置
 *
 * @author G. Seinfeld
 * @since 2020/06/08
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm daoRealm() {
        return new JdbcRealm();
    }
}
