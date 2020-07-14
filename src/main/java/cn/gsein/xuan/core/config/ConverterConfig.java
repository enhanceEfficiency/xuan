package cn.gsein.xuan.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 前端向后台传数据时特殊类型数据的转换器
 *
 * @author G. Seinfeld
 * @since 2020/07/14
 */
@Configuration
public class ConverterConfig {

    @Bean
    Converter<String, LocalDateTime> LocalDateTimeConverter() {
        // 注：此处不能转为lambda表达式，否则将无法读取到泛型信息，导致报错
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String s) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(s, formatter);
            }
        };
    }
}
