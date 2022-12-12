package com.zgy.jdkfiles.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public static int Chinese2Num(String s) {
        Map<Character, Integer> number_map = new HashMap<Character, Integer>() {
            {
                put('零', 0);
                put('一', 1);
                put('二', 2);
                put('三', 3);
                put('四', 4);
                put('五', 5);
                put('六', 6);
                put('七', 7);
                put('八', 8);
                put('九', 9);
            }
        };
        Map<Character, Integer> unit_map = new HashMap<Character, Integer>() {
            {
                put('十', 10);
                put('百', 100);
                put('千', 1000);
                put('万', 10000);
                put('亿', 100000000);
            }
        };
        int res = 0;
        int unit = 1;//当前一段数的单位
        int num = 0;//当前一段数
        for (int i = s.length() - 1; i >= 0; i--) {
            Character c = s.charAt(i);
            if (number_map.containsKey(c)) {
                num = number_map.get(c);
                res += num * unit;
            } else {
                unit = unit_map.get(c);
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("退出")) {

                System.exit(0);
            }

            int i = Chinese2Num(next);
            System.out.println(i);
        }

    }
}
