package cn.gsein.xuan.modules.generator;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 代码生成器入口类
 *
 * @author G. Seinfeld
 * @since 2020/07/04
 */
public class GeneratorMain {

    public static final String MODULES_PATH = "src/main/java/cn/gsein/xuan/modules";

    public static final String DAO = "dao";
    public static final String DAO_FILE_NAME = "Dao.java";
    public static final String DAO_TEMPLATE_PATH = "templates/velocity/Dao.java.vm";

    public static final String SERVICE = "service";
    private static final String SERVICE_FILE_NAME = "Service.java";
    private static final String SERVICE_TEMPLATE_PATH = "templates/velocity/Service.java.vm";

    private static final String SERVICE_IMPL = "service/impl";
    private static final String SERVICE_IMPL_FILE_NAME = "ServiceImpl.java";
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "templates/velocity/ServiceImpl.java.vm";

    private static final String CONTROLLER = "controller";
    private static final String CONTROLLER_FILE_NAME = "Controller.java";
    private static final String CONTROLLER_TEMPLATE_PATH = "templates/velocity/Controller.java.vm";

    public static void main(String[] args) {
        // 准备向模板中替换的数据
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
                .author("G. Seinfeld").date(getTodayStr()).comment("部门")
                .className("Dept").moduleName("system").baseUrl("/sys/dept")
                .build();
        Map<String, Object> map = Convert.toMap(String.class, Object.class, generatorConfig);

        // Velocity引擎
        final VelocityEngine engine = new VelocityEngine();
        engine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        engine.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, true); // 使用缓存
        engine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init();

        // Velocity工具管理器
        ToolManager toolManager = new ToolManager();
        EasyFactoryConfiguration configuration = new EasyFactoryConfiguration();
        configuration.autoLoad();
        toolManager.configure(configuration);
        ToolContext context = toolManager.createContext();
        context.putAll(map);

        // 当前工程根路径
        String projectPath = System.getProperty("user.dir");


        // 输出文件
        File outputFile = Paths.get(projectPath, MODULES_PATH,
                generatorConfig.getModuleName(), DAO, generatorConfig.getClassName() + DAO_FILE_NAME).toFile();


        // 替换模板数据，渲染并生成文件
        Template template = engine.getTemplate(DAO_TEMPLATE_PATH);
        render(context, outputFile, template);


        // 生成service接口
        outputFile = Paths.get(projectPath, MODULES_PATH, generatorConfig.getModuleName(), SERVICE,
                generatorConfig.getClassName() + SERVICE_FILE_NAME).toFile();

        template = engine.getTemplate(SERVICE_TEMPLATE_PATH);
        render(context, outputFile, template);

        // 生成service实现类
        outputFile = Paths.get(projectPath, MODULES_PATH, generatorConfig.getModuleName(), SERVICE_IMPL,
                generatorConfig.getClassName() + SERVICE_IMPL_FILE_NAME).toFile();
        template = engine.getTemplate(SERVICE_IMPL_TEMPLATE_PATH);
        render(context, outputFile, template);

        // 生成Controller类
        outputFile = Paths.get(projectPath, MODULES_PATH, generatorConfig.getModuleName(), CONTROLLER,
                generatorConfig.getClassName() + CONTROLLER_FILE_NAME).toFile();
        template = engine.getTemplate(CONTROLLER_TEMPLATE_PATH);
        render(context, outputFile, template);
    }

    private static void render(ToolContext context, File outputFile, Template template) {
        try (BufferedOutputStream out = FileUtil.getOutputStream(outputFile)) {
            OutputStreamWriter writer = IoUtil.getWriter(out, "UTF-8");
            template.merge(context, writer);
            IoUtil.flush(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取yyyy/MM/dd格式的今天日期
     */
    private static String getTodayStr() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return formatter.format(localDate);
    }
}
