package com.example.pojo;

import java.util.Objects;

/**
 * @ProjectName practice
 * @PackageName com.example.pojo
 * @ClassName User
 * @Author zhanggeyang
 * @Date 2022-01-07 19:20
 * @Description
 * @Version 1.0
 */


public class User {
    private String name;
    private String age;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                age.equals(user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
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

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
