package com.example.redis;

import com.example.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {


    private static final String string = "string";
    private static final String stringExpire = "stringExpire";
    private static final String object = "object";
    private static final String map = "map";
    private static final String mapExpire = "mapExpire";


    @Autowired()
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    /*
     * @Author: zhanggeyang
     * @Description:  新增元素
     * @Date: 12:55 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void putString() {
        //如果有key，则覆盖原来的value，否则新增
        redisTemplate.opsForValue().set(string, "zhangsan111");
    }

    @Test
    public void putStringIfAbsent() {
        //如果不存在key，则设置value
        redisTemplate.opsForValue().setIfAbsent(string, "zhangsan222");
        //如果存在key，则设置value
        redisTemplate.opsForValue().setIfPresent(string, "zhangsan33");
    }

    @Test
    public void getString() {
        Object name1 = redisTemplate.opsForValue().get(string);
        System.out.println(name1);
        //这种方式会出现反序列化失败
    }

    @Test
    public void putStringWithExpire() {
        //存有过期时间
        redisTemplate.opsForValue().set(stringExpire, "lisi", 10, TimeUnit.SECONDS);
    }

    @Test
    public void putStringObject() {
        User user = new User("lisi", "18");
        redisTemplate.opsForValue().set(object + "String11", user);
        System.out.println(redisTemplate.opsForValue().get(object + "String11"));
    }


    @Test
    public void putHash() {
        redisTemplate.opsForHash().put(map, "name", "zhangsan");
    }

    @Test
    public void putHashIfAbsent() {
        //hashkey不存在的时候设置，存在则不设置
        redisTemplate.opsForHash().put("putHashIfAbsent", "name", "zhangsan111");
    }

    @Test
    public void getHash() {
        Object key = redisTemplate.opsForHash().get(map, "name");
        System.out.println(key);
    }

    @Test
    public void putHashObject() {
        //使用redis的hash类型存储对象
        User user = new User("lisi", "18");
        redisTemplate.opsForHash().put(object, "user1", user);
    }

    @Test
    public void getHashObject() {
        //根据rediskey获取对象类型的value
        Object o = redisTemplate.opsForHash().get(object, "user1");
        System.out.println(o);
    }

    @Test
    public void deleteHash() {
        //根据rediskey和hashkey删除对应元素，可传入多个hashkey
        redisTemplate.opsForHash().delete(map, "key", "key2");
    }

    @Test
    public void hashKeys() {
        //获取rediskey对应的map容量大小
        Long size = redisTemplate.opsForHash().size(map);
        System.out.println(size);
    }

    @Test
    public void hashValues() {
        //根据rediskey获取所有的map的value集合
        List values = redisTemplate.opsForHash().values("mapPutAll");
        System.out.println(values);
    }

    @Test
    public void mapPutAll() {
        //一次性存入多个map
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put("1", 1);
        stringIntegerHashMap.put("2", 2);
        stringIntegerHashMap.put("3", 3);
        redisTemplate.opsForHash().putAll("mapPutAll", stringIntegerHashMap);
    }

    @Test
    public void getAllMap() {
        //根据rediskey获取所有的map
        System.out.println(redisTemplate.opsForHash().entries("mapPutAll"));
    }

    @Test
    public void putList() {
        for (int i = 0; i < 10; i++) {
            redisTemplate.opsForList().rightPush("list", i);
        }
    }

    @Test
    public void popList() {
        //弹出rediskey对应的元素，有count参数时，弹出对应的数量，默认为弹出一个
        //System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().leftPop("list", 5));
    }

    @Test
    public void pushAll() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }

        for (int i = 10; i < 20; i++) {
            list2.add(i);
        }
        redisTemplate.opsForList().rightPushAll("user1-list", list1);
        redisTemplate.opsForList().rightPushAll("user2-list", list2);
    }


    @Test
    public void getElement() {
        //-1表示查询全部
        List user1 = redisTemplate.opsForList().range("user1", 0, -1);
        //包含end 总共100-0+1个元素
        List user2 = redisTemplate.opsForList().range("user1", 0, 100);
        System.out.println(user2.size());

    }

    @Test
    public void modifyElement() {
        redisTemplate.opsForList().set("user1", 0, "abcd");

    }

    @Test
    public void rightPopAndLeftPush() {
        //将user1的最后一个元素移动到user2的第一位
        redisTemplate.opsForList().rightPopAndLeftPush("user1", "user2");

    }

    @Test
    public void indexOf() {
        //返回指定元素的索引值
        Long user1 = redisTemplate.opsForList().indexOf("user1", 5);
        System.out.println(user1);
    }

    @Test
    public void index() {
        //返回指定索引对应的value
        Object user2 = redisTemplate.opsForList().index("user2", 8);
        System.out.println(user2);
    }

    @Test
    public void move1() {
        //从user1 的头部 移动一个元素到user2 的尾部
        //redisTemplate.opsForList().move(ListOperations.MoveFrom.fromHead("user1"), ListOperations.MoveTo.toTail("user2"));
        //从user1 的尾部 移动一个元素到user2 的头部 ，等价于 rightPopAndLeftPush
        redisTemplate.opsForList().move(ListOperations.MoveFrom.fromTail("user1"), ListOperations.MoveTo.toHead("user2"));
    }

    @Test
    public void move2() {
        //将user1 的第一个元素添加到user2 的最后一位。
        redisTemplate.opsForList().move("user1", RedisListCommands.Direction.LEFT, "user2", RedisListCommands.Direction.RIGHT);
    }

    @Test
    public void move3() {
        //将user1 的最后一个元素添加到user2 的第一位。
        redisTemplate.opsForList().move("user2", RedisListCommands.Direction.LEFT, "user1", RedisListCommands.Direction.RIGHT);
    }


    @Test
    public void setAdd() {
        //redisTemplate.opsForSet().add("user1-set", 1,2,5,4,8,6,2,9);
        //redisTemplate.opsForSet().add("user2-set", 1,2,3,4,5,6,7);
        redisTemplate.opsForSet().add("user3-set", 4, 5, 6, 7, 0, 23, 43);

    }

    @Test
    public void add() {
        //不会重复添加
        redisTemplate.opsForSet().add("user1-set", 5);
    }


    /*
     * @Author: zhanggeyang
     * @Description: 求两个集合的差集，结果和集合的先后顺序有关
     * @Date: 12:05 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void getDifference() {
        //user1相对于user2的差集，user1 的元素减去user2 的元素
        //Set difference = redisTemplate.opsForSet().difference("user1-set", "user2-set");

        //user2相对于user1的差集，user2 的元素减去user1 的元素
        Set difference = redisTemplate.opsForSet().difference("user2-set", "user1-set");

        //获取集合的差集并存到第三个集合中，返回差集元素的个数
        Long diff = redisTemplate.opsForSet().differenceAndStore("user2-set", "user1-set", "diff");
        System.out.println(diff);
        System.out.println(difference);

    }

    /*
     * @Author: zhanggeyang
     * @Description: 求两个集合的交集
     * @Date: 12:06 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void getInner() {
        //求集合的交集
        Set intersect = redisTemplate.opsForSet().intersect("user2-set", "user1-set");

        Set members2 = redisTemplate.opsForSet().members("user2-set");
        Set members3 = redisTemplate.opsForSet().members("user3-set");
        ArrayList<Set> sets = new ArrayList<>();
        sets.add(members2);
        sets.add(members3);
        Set intersect1 = redisTemplate.opsForSet().intersect(1, sets);
        System.out.println(intersect1);

        //获取交集并将结果存到第三个集合，返回交集元素数量
        long inner = redisTemplate.opsForSet().intersectAndStore("user2-set", "user1-set", "inner");
        System.out.println(inner);
        System.out.println(intersect);
    }


    /*
     * @Author: zhanggeyang
     * @Description:  求两个集合的并集
     * @Date: 12:17 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void union() {
        Set union = redisTemplate.opsForSet().union("user1-set", "user2-set");

        long store = redisTemplate.opsForSet().unionAndStore("user1-set", "user2-set", "union");
        System.out.println(store);
    }

    /*
     * @Author: zhanggeyang
     * @Description:  随机返回一个或者多个元素
     * @Date: 12:15 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void distinctRandomMembers() {
        Set set = redisTemplate.opsForSet().distinctRandomMembers("user1-set", 5);
        System.out.println(set);
    }

    /*
     * @Author: zhanggeyang
     * @Description:   获取指定key的所有元素
     * @Date: 12:49 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void getMembers() {
        Set members = redisTemplate.opsForSet().members("user1-set");
        System.out.println(members);
    }

    /*
     * @Author: zhanggeyang
     * @Description:   检查给定的元素是否是集合的元素
     * @Date: 12:52 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void isMember() {
        Boolean member = redisTemplate.opsForSet().isMember("user1-set", 2);
        System.out.println(member);
    }

    /*
     * @Author: zhanggeyang
     * @Description: 随机返回一个元素
     * @Date: 12:54 上午 1/8/22
     * @Param: []
     * @Return: void
     */
    @Test
    public void randomMember() {
        Object o = redisTemplate.opsForSet().randomMember("user1-set");
        System.out.println(o);
    }


    @Test
    public void method() {

    }

}
