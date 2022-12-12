import entity.Actor;
import entity.Menu;
import org.apache.http.client.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(200);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (i < 3) {
                user.setCreateTime(Date.from(new Date().toInstant().plus(i * 30, ChronoUnit.DAYS)));
            } else if (i >= 3 && i < 11){
                user.setCreateTime(Date.from(new Date().toInstant().plus(i, ChronoUnit.DAYS)));
            }
            users.add(user);
        }

        for (User user : users) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(user.getCreateTime()));
            Date createTime = user.getCreateTime();
            Calendar instance = Calendar.getInstance();
            instance.setTime(createTime);
            int year = instance.get(Calendar.YEAR);
        }




    }

    static class User {
        private long id;
        private Date createTime;

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("createTime=" + createTime)
                    .toString();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public User() {
        }

        public User(long id, Date createTime) {
            this.id = id;
            this.createTime = createTime;
        }
    }

    private static void getTree() {
        List<Menu> menuList = Arrays.asList(
                new Menu(1, "根节点", 0, LocalDateTime.of(2020, 01, 03, 14, 25)),
                new Menu(2, "子节点1", 1, LocalDateTime.of(2021, 01, 05, 18, 25)),
                new Menu(3, "子节点1.1", 2, LocalDateTime.of(2018, 07, 03, 14, 25)),
                new Menu(4, "子节点1.2", 2, LocalDateTime.of(2021, 01, 03, 14, 25)),
                new Menu(5, "子节点1.3", 2, LocalDateTime.of(2020, 8, 03, 14, 25)),
                new Menu(6, "子节点2", 1, LocalDateTime.of(2020, 9, 05, 14, 25)),
                new Menu(7, "子节点2.1", 6, LocalDateTime.of(2020, 01, 03, 14, 25)),
                new Menu(8, "子节点2.2", 6, LocalDateTime.of(2018, 01, 03, 14, 25)),
                new Menu(9, "子节点2.2.1", 7, LocalDateTime.of(2002, 01, 03, 14, 25)),
                new Menu(10, "子节点2.2.2", 7, LocalDateTime.of(2018, 01, 03, 14, 25)),
                new Menu(11, "子节点3", 1, LocalDateTime.of(2016, 01, 03, 14, 25)),
                new Menu(12, "子节点3.1", 11, LocalDateTime.of(2017, 01, 03, 14, 25))
        );

        //所有的节点，获取父节点
        List<Menu> collect = menuList.parallelStream().filter(e -> e.getPid() == 0).map(
                e -> {
                    e.setChildList(getChildList(e, menuList));
                    return e;
                }
        ).collect(Collectors.toList());

        //  System.out.println(collect);
        /*ObjectMapper objectMapper = new ObjectMapper();
        try {
            String string = objectMapper.writeValueAsString(collect);
            System.out.println(string);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

    }

    private static List<Menu> getChildList(Menu menu, List<Menu> menuList) {

        List<Menu> collect = menuList.parallelStream()
                .filter(e -> Objects.deepEquals(e.getPid(), menu.getId()))
                .sorted(Comparator.comparing(Menu::getCreatTime).reversed())
                .map(e -> {
                            e.setChildList(getChildList(e, menuList));
                            return e;
                        }
                ).collect(Collectors.toList());
        return collect;
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
        List<String> collect = arrayList.stream().sorted((o1, o2) -> Integer.parseInt(o1) - Integer.valueOf(o2)).collect(Collectors.toList());

        System.out.println(collect);
    }

    private static void integerTest() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);
        /*arrayList.add(1);
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
        arrayList.add(3);*/
        long start1 = System.currentTimeMillis();
        List<Integer> collect = arrayList.stream().
                filter(num -> num > 50).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());

        //System.out.println(collect);

        long end1 = System.currentTimeMillis();
        List<Integer> collect1 = arrayList.parallelStream().
                filter(num -> num > 50).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());

        long end2 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.out.println(end2 - end1);
    }


    public static void testLong() {
        ArrayList<Long> list = new ArrayList<Long>(200000);
        //CopyOnWriteArrayList<Long> list = new CopyOnWriteArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5000000; i++) {

            list.add(Long.valueOf(i));
        }
        long l = System.currentTimeMillis();
        List<Long> collect = list.stream().filter(e -> e > 5000).sorted().collect(Collectors.toList());
        long l1 = System.currentTimeMillis();
        List<Long> collect1 = list.parallelStream().filter(e -> e > 5000).sorted().collect(Collectors.toList());
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        System.out.println(l1 - l);
    }

    public static void test() {
        String[] xing = {"张", "王", "李", "陈", "赵", "钱", "孙", "周", "张", "王", "李", "陈", "张", "王", "李", "陈", "赵", "钱", "孙", "周", "张", "王", "李", "陈", "张", "王", "李", "陈", "赵", "钱", "孙", "周", "张", "王", "李", "陈"};

        String[] ming = {"苑", "杰毅", "寒", "君乾", "新远", "智", "宇灿", "弘", "涛羽", "翰", "鼎智", "杰", "苑", "杰毅", "寒", "君乾", "新远", "智", "宇灿", "弘", "涛羽", "翰", "鼎智", "杰", "苑", "杰毅", "寒", "君乾", "新远", "智", "宇灿", "弘", "涛羽", "翰", "鼎智", "杰", "苑", "杰毅", "寒", "君乾", "新远", "智", "宇灿", "弘", "涛羽", "翰", "鼎智", "杰"};

        ArrayList<String> nameList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            String xin = xing[new Random().nextInt(xing.length)];
            String min = ming[new Random().nextInt(ming.length)];
            nameList.add(xin + min);
        }

        long l = System.currentTimeMillis();
        List<String> collect = nameList.stream().filter(e -> e.contains("张") || e.contains("君")).filter(e -> e.length() == 2).filter(e -> e.contains("杰")).distinct().sorted().collect(Collectors.toList());
        long l1 = System.currentTimeMillis();
        List<String> collect1 = nameList.parallelStream().filter(e -> e.contains("张") || e.contains("君")).filter(e -> e.length() == 2).filter(e -> e.contains("杰")).distinct().sorted().collect(Collectors.toList());
        long l2 = System.currentTimeMillis();
        System.out.println(l1 - l);
        System.out.println(l2 - l1);
    }

    public static void test1() {
        String actor_man_one = "周润发";
        String actor_man_two = "成龙";
        String actor_man_three = "刘德华";
        String actor_man_four = "吴京";
        String actor_man_five = "周星驰";
        String actor_man_six = "李连杰";
        ArrayList<String> actors_man = new ArrayList<String>();
        actors_man.add(actor_man_one);
        actors_man.add(actor_man_two);
        actors_man.add(actor_man_three);
        actors_man.add(actor_man_five);
        actors_man.add(actor_man_four);
        actors_man.add(actor_man_six);
        String actor_woman_one = "林心如";
        String actor_woman_two = "张曼玉";
        String actor_woman_three = "杨超越";
        String actor_woman_four = "林志玲";
        String actor_woman_five = "周冬雨";
        String actor_woman_six = "杨颖";
        ArrayList<String> actors_woman = new ArrayList<String>();
        actors_woman.add(actor_woman_one);
        actors_woman.add(actor_woman_two);
        actors_woman.add(actor_woman_three);
        actors_woman.add(actor_woman_four);
        actors_woman.add(actor_woman_five);
        actors_woman.add(actor_woman_six);

        Stream<String> actorStream_man = actors_man.stream().filter(actor -> actor.length() == 3).limit(2);

        Stream<String> actorStream_woman = actors_woman.stream().filter(actor -> actor.startsWith("杨")).skip(1);
        Stream.concat(actorStream_man, actorStream_woman).forEach(s -> {
            Actor actor = new Actor(s);
            System.out.println(actor);
        });
    }


}
