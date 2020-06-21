package cn.gsein.xuan.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本系统相关配置
 *
 * @author G. Seinfeld
 * @since 2020/06/21
 */
@Component
@ConfigurationProperties("xuan")
public class XuanConfig {
    /**
     * 上传路径
     */
    private static String uploadPath;

    public static String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        XuanConfig.uploadPath = uploadPath;
    }
}
