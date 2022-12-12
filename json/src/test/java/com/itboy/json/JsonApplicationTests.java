package com.itboy.json;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itboy.json.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
        user.setName("tony");
        user.setAddress("beijing");
        user.setBirthday(LocalDateTime.now());
        objectMapper.findAndRegisterModules();
        String string = objectMapper.writeValueAsString(user);
        System.out.println(string);
    }

    @Test
    public void jacksonString2Object() throws JsonProcessingException {
        String json = "{\"name\":\"tony\",\"address\":\"beijing\",\"birthday\":\"2026-01-26 19:13:10\"}";
        objectMapper.findAndRegisterModules();
        System.out.println(objectMapper);
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }
}
