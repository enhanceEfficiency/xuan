package cn.gsein.xuan.modules.generator;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author G. Seinfeld
 * @since 2020/07/09
 */
public class CodeGenerator {
    private static final String MODULES_PATH = "src/main/java/cn/gsein/xuan/modules";

    private static final String DAO = "dao";
    private static final String DAO_FILE_NAME = "Dao.java";
    private static final String DAO_TEMPLATE_PATH = "templates/velocity/Dao.java.vm";

    private static final String SERVICE = "service";
    private static final String SERVICE_FILE_NAME = "Service.java";
    private static final String SERVICE_TEMPLATE_PATH = "templates/velocity/Service.java.vm";

    private static final String SERVICE_IMPL = "service/impl";
    private static final String SERVICE_IMPL_FILE_NAME = "ServiceImpl.java";
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "templates/velocity/ServiceImpl.java.vm";

    private static final String CONTROLLER = "controller";
    private static final String CONTROLLER_FILE_NAME = "Controller.java";
    private static final String CONTROLLER_TEMPLATE_PATH = "templates/velocity/Controller.java.vm";

    public void generate(GeneratorData data) {
        Map<String, Object> map = Convert.toMap(String.class, Object.class, data);

        // 初始化Velocity引擎
        final VelocityEngine engine = initVelocityEngine();

        // 根据数据创建上下文
        Context context = createContext(map);

        // 当前工程根路径
        String projectPath = System.getProperty("user.dir");

        // 生成dao接口
        generateDao(data, engine, context, projectPath);

        // 生成service接口
        generateService(data, engine, context, projectPath);

        // 生成service实现类
        generateServiceImpl(data, engine, context, projectPath);

        // 生成Controller类
        generateController(data, engine, context, projectPath);
    }

    private void generateController(GeneratorData data, VelocityEngine engine, Context context, String projectPath) {
        File outputFile = Paths.get(projectPath, MODULES_PATH, data.getModuleName(), CONTROLLER,
                data.getClassName() + CONTROLLER_FILE_NAME).toFile();
        Template template = engine.getTemplate(CONTROLLER_TEMPLATE_PATH);
        render(context, outputFile, template);
    }

    private void generateServiceImpl(GeneratorData data, VelocityEngine engine, Context context, String projectPath) {
        File outputFile = Paths.get(projectPath, MODULES_PATH, data.getModuleName(), SERVICE_IMPL,
                data.getClassName() + SERVICE_IMPL_FILE_NAME).toFile();
        Template template = engine.getTemplate(SERVICE_IMPL_TEMPLATE_PATH);
        render(context, outputFile, template);
    }

    private void generateService(GeneratorData data, VelocityEngine engine, Context context, String projectPath) {
        File outputFile = Paths.get(projectPath, MODULES_PATH, data.getModuleName(), SERVICE,
                data.getClassName() + SERVICE_FILE_NAME).toFile();

        Template template = engine.getTemplate(SERVICE_TEMPLATE_PATH);
        render(context, outputFile, template);
    }

    private void generateDao(GeneratorData data, VelocityEngine engine, Context context, String projectPath) {
        File outputFile = Paths.get(projectPath, MODULES_PATH,
                data.getModuleName(), DAO, data.getClassName() + DAO_FILE_NAME).toFile();
        Template template = engine.getTemplate(DAO_TEMPLATE_PATH);
        render(context, outputFile, template);
    }

    private Context createContext(Map<String, Object> map) {
        // Velocity工具管理器
        ToolManager toolManager = new ToolManager();

        // 加载默认工具
        EasyFactoryConfiguration configuration = new EasyFactoryConfiguration();
        configuration.autoLoad();
        toolManager.configure(configuration);

        // 将数据放入上下文中，准备替换模板
        ToolContext context = toolManager.createContext();
        context.putAll(map);
        return context;
    }

    private VelocityEngine initVelocityEngine() {
        final VelocityEngine engine = new VelocityEngine();
        engine.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        engine.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, true); // 使用缓存
        engine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init();
        return engine;
    }

    private void render(Context context, File outputFile, Template template) {
        try (BufferedOutputStream out = FileUtil.getOutputStream(outputFile)) {
            OutputStreamWriter writer = IoUtil.getWriter(out, "UTF-8");
            template.merge(context, writer);
            IoUtil.flush(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
