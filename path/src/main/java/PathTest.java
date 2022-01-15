import org.springframework.boot.system.ApplicationHome;

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
        ApplicationHome applicationHome = new ApplicationHome(PathTest.class);
        System.out.println(applicationHome.getDir());
    }
}
