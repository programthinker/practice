package com.itboy.timetest;

import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TimeTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        //2022-04-01T18:46:34.993
        LocalDateTime now = LocalDateTime.now();

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2022, 04, 02), LocalTime.of(20, 00));

        //现在时间是否在构造时间之后
        boolean after = now.isAfter(dateTime);
        //现在时间是否在构造时间之前
        boolean before = now.isBefore(dateTime);
        //现在时间是否等于构造时间
        boolean equal = now.isEqual(dateTime);


        //获取日期时间的年份
        System.out.println("今天是" + now.getYear() + "年" + now.getMonthValue() + "月" + now.getDayOfMonth() + "号，星期" + now.getDayOfWeek().getValue() + "，今年的第" + now.getDayOfYear() + "天");

        //现在时间减去1天
        LocalDateTime localDateTime = now.minusDays(1);
        //现在日期减去1个月
        LocalDateTime localDateTime1 = now.minusMonths(1);
        LocalDateTime minus = now.minus(1, ChronoUnit.MONTHS);
        //现在日期减去1年
        LocalDateTime minusYears = now.minusYears(1);

        //现在时间加上1天
        LocalDateTime plusDays = now.plusDays(1);
        //现在时间加上0年，5个月，0天
        LocalDate plusDate = nowDate.plus(Period.of(0, 5, 0));

        //现在时间加上1小时，30分钟，30秒
        LocalTime plusTime = nowTime.plus(Duration.ofHours(1)).plus(Duration.ofMinutes(30)).plus(Duration.ofSeconds(00));


        LocalDate localDate = LocalDate.now();
        LocalDate plusDate1 = localDate.plus(Period.of(0, 1, 1));
        LocalTime localTime = LocalTime.now();
        LocalTime plusTime1 = localTime.plus(Duration.ofHours(10)).plus(Duration.ofMinutes(0)).plus(Duration.ofSeconds(20));

        System.out.println(localTime);
        System.out.println(plusTime1);

        LocalDateTime start = LocalDateTime.of(localDate, localTime);
        LocalDateTime end = LocalDateTime.of(plusDate1, plusTime1);


        //获取更改后的时间
        LocalDateTime result = LocalDateTime.of(plusDate, plusTime);
        Duration between = Duration.between(start.toLocalTime(), end.toLocalTime());
        Period between1 = Period.between(start.toLocalDate(), end.toLocalDate());
        System.out.println(
                between1.getYears() + "年" +
                        between1.getMonths() + "月" +
                        between1.getDays() + "日" +
                        between.toHours() + "小时" +
                        between.minus(60, ChronoUnit.MINUTES).toMinutes() + "分" +
                        between.minus(between.toHours() * 3600 + between.minus(60, ChronoUnit.MINUTES).toMinutes() * 60, ChronoUnit.SECONDS).getSeconds() + "秒");


        String format = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss").format(result);
        System.out.println(format);
        System.out.println(result);

    }

    @Test
    public void diff() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.of(2022, 4, 2, 8, 00, 00);
        Instant instantNow = now.toInstant(ZoneOffset.of("+8"));
        Instant instantAfter = after.toInstant(ZoneOffset.of("+8"));


    }


    @Test
    public void equels() {
        String a = "dyuTudk98MNHJ";
        String b = "dyuTudk98MNHj";
        System.out.println(a.equals(b));
    }

    @Test
    public void music() {
        File allFile = new File("/Volumes/storage/歌曲/JAY全集27专辑全收藏/周杰伦录音室专辑全集/专辑全集FLAC格式");

        File[] files = allFile.listFiles();

        //获取专辑名 在本目录下新建每个专辑的文件夹
        for (File file : files) {
            if (file.getName().contains("DS_Store")) {
                file.delete();
                continue;
            }

            String orginalName = file.getName();
            String[] split = null;
            if (orginalName.contains("-")) {
                split = orginalName.split("-");

                //Arrays.stream(split).forEach(s -> System.out.println(s));
                String ablumName = split[0].trim();

                if (ablumName.contains(".")) {
                    ablumName = ablumName.substring(4);
                }
                System.out.println(ablumName);
                String mp3Name = split[1].trim();
                new File(allFile, ablumName).mkdirs();
            } else {
                new File(allFile, file.getName());
            }


        }

        //分割后包涵专辑名的文件进行移动

        //移动完成后重命名

    }

    @Test
    public void move() {
        File allFile = new File("/Volumes/storage/歌曲/JAY全集27专辑全收藏/周杰伦录音室专辑全集/专辑全集FLAC格式");

        File[] files = allFile.listFiles();
        ArrayList<File> directory = new ArrayList<>();
        ArrayList<File> fileArrayList = new ArrayList<>();

        for (File file : files) {
            if (file.isDirectory()) {
                directory.add(file);
            }
            if (file.isFile()) {
                fileArrayList.add(file);
            }
        }
        for (File directoryFile : directory) {
            for (File file : fileArrayList) {
                String fileName = file.getName();
                String directoryFileName = directoryFile.getName();
                if (fileName.contains(directoryFileName)) {
                    String s = fileName.split("-")[1];
                    try {
                        FileUtils.writeByteArrayToFile(new File(directoryFile, s), FileUtils.readFileToByteArray(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void timestampToTime() {
        Long time = 1562983881098L;
        //获取10位时间戳
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        //获取13位时间戳
        long l2 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long l3 = System.currentTimeMillis();
        //System.out.println(l3);
        //System.out.println(l2);

        LocalDateTime time2 = LocalDateTime.ofEpochSecond(l, 0, ZoneOffset.ofHours(8));
        long l1 = time2.toEpochSecond(ZoneOffset.ofHours(8));
        //System.out.println(l1);
        System.out.println(time2);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localTime = df.format(time2);
        LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);

    }

    @Test
    public void epochSecondToDateTime() {
        long l = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(l, 0, ZoneOffset.ofHours(9));
        System.out.println(localDateTime);
    }

    @Test
    public void zoneID() {
        LocalDate localDateTime = LocalDate.parse("20220516", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(localDateTime);

    }

    @Test
    public void test1() {
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8081/login");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig aDefault = RequestConfig.DEFAULT;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).setConnectionRequestTimeout(5000).build();
        httpPost.setConfig(aDefault);
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("username", "admin"));
        paramList.add(new BasicNameValuePair("password", "admin"));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);

        httpPost.setEntity(formEntity);

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            Header[] headers = httpResponse.getHeaders("Set-Cookie");
            for (int i = 0; i < headers.length; i++) {
                System.out.println(headers[i].toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void test33(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (Integer integer : integerList) {
            if (integer.equals(2)){
                return;
            }
            System.out.println("hhhh"+integer);

        }
    }


    @Test
    public void test44(){

    }
}
