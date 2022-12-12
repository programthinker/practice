package com.example.pojo;

import java.util.StringJoiner;

/**
 * @ProjectName practice
 * @PackageName com.example.pojo
 * @ClassName Person
 * @Author zhanggeyang
 * @Date 2022-05-11 21:04
 * @Description
 * @Version 1.0
 */

public class Person {
    private String name;
    private String age;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age='" + age + "'")
                .toString();
    }

    public Person() {
    }

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
