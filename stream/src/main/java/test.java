import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName practice
 * @PackageName PACKAGE_NAME
 * @ClassName test
 * @Author zhanggeyang
 * @Date 2021-12-20 18:45
 * @Description
 * @Version 1.0
 */

public class test {
    public static void main(String[] args) {
        integerTest();
    }

    private static void stringTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("ddf");
        arrayList.add("bb");
        arrayList.add("tt");
        arrayList.add("rz");
        arrayList.add("gd");
        arrayList.add("gdf");
        arrayList.add("gdf");
        arrayList.add("2");
        arrayList.add("5g");
        arrayList.add("f5");
        arrayList.add("n6");
        List<String> collect = arrayList.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1) - Integer.valueOf(o2);
            }
        }).collect(Collectors.toList());

        System.out.println(collect);
    }

    private static void integerTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 50000000; i++) {
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);
        arrayList.add(1);
        arrayList.add(20);
        arrayList.add(34);
        arrayList.add(89);
        arrayList.add(4);
        arrayList.add(33);
        arrayList.add(87);
        arrayList.add(987);
        arrayList.add(44456);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(3);
        long start1 = System.currentTimeMillis();
        List<Integer> collect = arrayList.stream().
                filter(num -> num < 5000).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }).collect(Collectors.toList());


        long end1 = System.currentTimeMillis();
        List<Integer> collect1 = arrayList.parallelStream().
                filter(num -> num < 5000).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }).collect(Collectors.toList());

        long end2 = System.currentTimeMillis();


        System.out.println(end1-start1);
        System.out.println(end2-end1);
    }
}
