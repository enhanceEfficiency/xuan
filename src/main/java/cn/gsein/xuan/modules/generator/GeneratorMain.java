package cn.gsein.xuan.modules.generator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 代码生成器入口类
 *
 * @author G. Seinfeld
 * @since 2020/07/04
 */
public class GeneratorMain {

    public static void main(String[] args) {
        // 准备向模板中替换的数据
        GeneratorData generatorData = GeneratorData.builder()
                .author("G. Seinfeld").date(getTodayStr()).comment("部门")
                .className("Dept").moduleName("system").baseUrl("/sys/dept")
                .build();

        CodeGenerator generator = new CodeGenerator();
        generator.generate(generatorData);
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
