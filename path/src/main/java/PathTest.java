import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @ProjectName practice
 * @PackageName PACKAGE_NAME
 * @ClassName PathTest
 * @Author zhanggeyang
 * @Date 2021-12-23 17:10
 * @Description
 * @Version 1.0
 */

public class PathTest {
    private static final byte[] JPEG_MAGIC_NUMBER = {(byte) 0xFF, (byte) 0xD8};
    // PNG 文件的标识
    private static final byte[] PNG_MAGIC_NUMBER = {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};

    public static void main(String[] args) {
        String path = "/Users/zhanggeyang/Pictures/BingWallPaper/BingWallPaper-2023-05-07-Royal azaleas in bloom on Hwangmaesan Mountain, South Korea-4K.jpg";
        System.out.println(isImageComplete(path));


    }

    public static boolean isImageComplete(String imagePath) {
        try (FileInputStream fis = new FileInputStream(imagePath)) {
            // 检查文件头
            byte[] header = new byte[JPEG_MAGIC_NUMBER.length];
            fis.read(header);
            if (!compareByteArrays(header, JPEG_MAGIC_NUMBER)) {
                return false; // 不是 JPEG 文件
            }

            // 检查文件尾
            byte[] footer = new byte[PNG_MAGIC_NUMBER.length];
            fis.skip(fis.available() - PNG_MAGIC_NUMBER.length);
            fis.read(footer);
            return compareByteArrays(footer, PNG_MAGIC_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean compareByteArrays(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }


}
