package com.zgy.jdkfiles;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SpringBootTest
class JdkfilesApplicationTests {

    private static Path SOURCE_PATH = Paths.get("/Users/zhanggeyang/Downloads/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");
    private static Path TARGET_PATH1 =Paths.get( "/Users/zhanggeyang/Documents/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");
    private static Path TARGET_PATH2 =Paths.get( "/Users/zhanggeyang/Desktop/(www.LWgod.xyz).1080p.Men.in.Black.International.mkv");
    @Test
    void contextLoads() {
    }

    @Test
    public void copy(){

        try {
            Path path = Paths.get(URI.create("https://cn.bing.com/th?id=OHR.ChukchiSea_ZH-CN7218471261_UHD.jpg"));
            System.out.println(path.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){

    }
}
