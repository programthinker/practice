package com.itboy.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.itboy.json.entity.Home;
import com.itboy.json.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JsonApplicationTests {

    @Autowired
    @Qualifier("obm")
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void jacksonObject2String() throws JsonProcessingException {
        User user = new User();
//        user.setName("tony");
        user.setAddress("beijing");
        user.setBirthday(LocalDateTime.now());
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        user.setHome(new Home("13","陕西西安市", strings));
        user.setCodes(strings);
        String string = objectMapper.writeValueAsString(user);
        System.out.println(string);
    }

    @Test
    public void jacksonString2Object() throws JsonProcessingException {
        String json = "[{\"address\":\"beijing\",\"birthday\":\"2023-04-06 16:38:37\",\"home\":{\"size\":\"13\",\"address\":\"陕西西安市\"}},{\"address\":\"beijing\",\"birthday\":\"2023-04-05 16:38:37\",\"home\":{\"size\":\"13\",\"address\":\"陕西西安市\"}}]";
        String json1 = "[{\n" +
                "  \"address\" : \"beijing\",\n" +
                "  \"birthday\" : \"2023-04-06 17:25:07\",\n" +
                "  \"home\" : {\n" +
                "    \"size\" : \"13\",\n" +
                "    \"address\" : \"陕西西安市\",\n" +
                "    \"codes\" : [ \"1\", \"2\", \"3\", \"4\", \"5\" ]\n" +
                "  },\n" +
                "  \"codes\" : [ \"1\", \"2\", \"3\", \"4\", \"5\" ]\n" +
                "}]";
//        User user = objectMapper.readValue(json, User.class);
//        System.out.println(user);
        List<User> user1 = objectMapper.readValue(json1, new TypeReference<List<User>>() {});
        System.out.println(user1);

    }
}
