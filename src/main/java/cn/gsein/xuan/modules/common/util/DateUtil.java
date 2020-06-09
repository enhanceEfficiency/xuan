package cn.gsein.xuan.modules.common.util;

import java.time.LocalDate;

/**
 * @author G. Seinfeld
 * @since 2020/06/09
 */
public final class DateUtil {
    private DateUtil() {
    }

    public static LocalDate now() {
        return LocalDate.now();
    }

}
