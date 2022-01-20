package com.zgy.jdkfiles.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ProjectName practice
 * @PackageName com.zgy.jdkfiles.controller
 * @ClassName FilesController
 * @Author zhanggeyang
 * @Date 2022-01-18 18:23
 * @Description
 * @Version 1.0
 */

@RestController
@RequestMapping(value = "/files")
public class FilesController {

    @Value("${fileLocation.location1}")
    private String location1;
    @Value("${fileLocation.location2}")
    private String location2;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String downLoad(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String fileName = file.getOriginalFilename();
        try {
            String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
            File target = new File(path + File.separator + "fileStorage");
            target.mkdirs();
            Path path1 = Paths.get(target.getPath() + File.separator + fileName);
            Files.copy(file.getInputStream(), path1, StandardCopyOption.REPLACE_EXISTING);
            return path1.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    @GetMapping(value = "/download")
    public void download(@RequestParam String filePath, HttpServletResponse response) throws Exception {

        // path是指想要下载的文件的路径
        File file = new File(filePath);
        // 将文件写入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);


        // 清空response
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString()));
        // 告知浏览器文件的大小
        response.addHeader("Content-Length", String.valueOf(file.length()));
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(outputStream);


    }

    @GetMapping(value = "/openOnline")
    public void openOnline(@RequestParam String filePath, HttpServletResponse response) throws Exception {

        // path是指想要下载的文件的路径
        File file = new File(filePath);
        // 将文件写入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);

        // 清空response
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());


        URL u = new URL("file:///" + file);
        response.setContentType(u.openConnection().getContentType());
        response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8.toString()));

        outputStream.write(buffer);
        outputStream.flush();

        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(outputStream);


    }
}
