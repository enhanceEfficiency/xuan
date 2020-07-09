package cn.gsein.xuan.modules.generator;

import lombok.Builder;
import lombok.Data;

/**
 * 代码生成器配置的实体
 *
 * @author G. Seinfeld
 * @since 2020/07/04
 */
@Data
@Builder
public class GeneratorData {

    /**
     * 模块名，如system
     */
    private String moduleName;

    /**
     * 类名，如Role
     */
    private String className;

    /**
     * 类的作用描述，如角色
     */
    private String comment;

    /**
     * 作者名，如G. Seinfeld
     */
    private String author;

    /**
     * 日期，如2020/07/04
     */
    private String date;

    /**
     * Controller基础url，如/sys/role
     */
    private String baseUrl;


}
