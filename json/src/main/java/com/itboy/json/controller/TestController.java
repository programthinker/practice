package com.itboy.json.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itboy.json.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @ProjectName practice
 * @PackageName com.itboy.json.controller
 * @ClassName TestController
 * @Author zhanggeyang
 * @Date 2022-01-26 19:53
 * @Description
 * @Version 1.0
 */

@RestController
public class TestController {

    @Autowired
    @Qualifier("obm")
    private ObjectMapper objectMapper;

    @GetMapping(value = "test")
    public String test() throws Exception{

        User user = new User();
        user.setName("tony");
        user.setAddress("beijing");
        user.setBirthday(LocalDateTime.now());
        System.out.println(user);
        String string = objectMapper.writeValueAsString(user);
        

        return string;
    }

    @GetMapping(value = "get")
    public void get(String s) throws Exception{
        User user = objectMapper.readValue(s, User.class);
        System.out.println(user);
    }
    @PostMapping(value = "post")
    public void get( User user) throws Exception{
        String s = objectMapper.writeValueAsString(user);

        System.out.println(s);
        User user1 = objectMapper.readValue(s, User.class);
        System.out.println(user1);
    }
}
