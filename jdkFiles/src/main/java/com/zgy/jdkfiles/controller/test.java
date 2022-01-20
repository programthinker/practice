package com.zgy.jdkfiles.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ProjectName practice
 * @PackageName com.zgy.jdkfiles.controller
 * @ClassName test
 * @Author zhanggeyang
 * @Date 2022-01-18 19:12
 * @Description
 * @Version 1.0
 */

public class test {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/Users/zhanggeyang/Downloads/ByWave-0.6.0.dmg");
        Path target = Paths.get("/Users/zhanggeyang/Documents/ByWave-0.6.0.dmg");
        File file = new File("/Users/zhanggeyang/Downloads/ByWave-0.6.0.dmg");
        File file1 = new File("/Users/zhanggeyang/Desktop/ByWave-0.6.0.dmg");


        long start = System.currentTimeMillis();

        Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING);

        long start1 = System.currentTimeMillis();

        System.out.println(start1 - start);

        FileUtils.copyFile(file,file1, StandardCopyOption.REPLACE_EXISTING);

        System.out.println(System.currentTimeMillis()-start1);

    }
}
