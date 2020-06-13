package cn.gsein.xuan.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 日期和时间处理的工具类
 *
 * @author G. Seinfeld
 * @since 2020/06/09
 */
public final class DateUtil {
    private DateUtil() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static Date nowDate() {
        return new Date();
    }

    /**
     * 将LocalDateTime转为Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

}
