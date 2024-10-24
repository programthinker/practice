package com.zgy.jdkfiles;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SpringBootTest
class JdkfilesApplicationTests {

    private static Path SOURCE_PATH = Paths.get("/Users/zhanggeyang/Downloads/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");
    private static Path TARGET_PATH1 = Paths.get("/Users/zhanggeyang/Documents/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");
    private static Path TARGET_PATH2 = Paths.get("/Users/zhanggeyang/Desktop/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");

    @Test
    void contextLoads() {
    }

    @Test
    public void copy() {

        try {
            Path path = Paths.get(URI.create("https://cn.bing.com/th?id=OHR.ChukchiSea_ZH-CN7218471261_UHD.jpg"));
            System.out.println(path.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read() {
        String filePath = "/Users/zhanggeyang/Downloads/【kkkanba】The.Pig.the.Snake.and.the.Pigeon.2023.1080p.NF.WEB-DL.DDP5.1.H264-HHWEB.mp4";
        String newfilePath = "/Users/zhanggeyang/Downloads/【kkkanba】The.Pig.the.Snake.and.the.Pigeon.2023.1080p.NF.WEB-DL.DDP5.1.H264-HHWEB-1.mp4";
        String newfilePath1 = "/Users/zhanggeyang/Downloads/【kkkanba】The.Pig.the.Snake.and.the.Pigeon.2023.1080p.NF.WEB-DL.DDP5.1.H264-HHWEB-2.mp4";
//        String filePath = "/Users/zhanggeyang/Downloads/MIAA-465 與姪女們溫泉旅行一起在男湯入浴中、被妹子雙重臀部夾擊10發惡作劇射精的我 松本一香 工藤拉拉 - Jable.TV | 免費高清AV在線看 | J片 AV看到飽.mp4";
        try(FileInputStream fis = new FileInputStream(filePath);
            FileOutputStream fos = new FileOutputStream(newfilePath)) {
            FileChannel sourceChannel = fis.getChannel();
            FileChannel targetChannel = fos.getChannel();
            MappedByteBuffer mappedByteBuffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChannel.size());
            
            targetChannel.write(mappedByteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long l = System.currentTimeMillis();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             FileOutputStream outputStream = new FileOutputStream(newfilePath)) {
            ByteBuffer buf = ByteBuffer.allocate(20480);

            while (fileInputStream.getChannel().read(buf) != -1) {
                buf.flip();//切换到读取数据模式
                outputStream.getChannel().write(buf);
                buf.clear();//清空缓冲区
            }


            System.out.println(System.currentTimeMillis() - l);

            long l1 = System.currentTimeMillis();
            FileUtils.copyFile(new File(filePath), new File(newfilePath));
//            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            System.out.println(System.currentTimeMillis() - l1);
//            System.out.println(bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
