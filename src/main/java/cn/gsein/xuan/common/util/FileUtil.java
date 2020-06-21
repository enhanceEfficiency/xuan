package cn.gsein.xuan.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 文件处理相关工具类
 *
 * @author G. Seinfeld
 * @since 2020/06/21
 */
public final class FileUtil {
    private FileUtil() {
    }

    /**
     * 将目标上传至upload文件夹
     *
     * @param files 前端传来的文件
     * @return 是否上传成功
     */
    public static boolean upload(MultipartFile[] files) {
        try (Stream<MultipartFile> fileStream = Stream.of(files)) {
            return fileStream.parallel().map(FileUtil::upload)
                    .reduce(true, Boolean::logicalAnd);
        }
    }

    public static boolean upload(MultipartFile file) {
        //FIXME
        Path dest = Paths.get(null);

        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
