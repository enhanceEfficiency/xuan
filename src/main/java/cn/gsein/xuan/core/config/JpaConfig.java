package cn.gsein.xuan.core.config;

import cn.gsein.xuan.common.util.DataAccessHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author G. Seinfeld
 * @since 2020/06/18
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig {

    @Bean
    public DataAccessHelper helper(){
        return new DataAccessHelper();
    }
}
