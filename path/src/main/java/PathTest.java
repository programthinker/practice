import org.springframework.boot.system.ApplicationHome;

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
    public static void main(String[] args) {
//        //子转父
//        Father father = new Sun();
//        father.eat();
//        //父转子
//        Sun sun = (Sun) new Father();
//        sun.eat();
        System.out.println(Objects.isNull(" "));
        System.out.println(Objects.equals("", ""));
        System.out.println(Objects.deepEquals("aa", "aa"));
        System.out.println(Objects.hashCode(""));
    }
}
