package com.itboy.elasticsearchtest.entity;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.entity
 * @ClassName: User
 * @Date: 2021年08月05日 17:58
 * @Author: zhanggeyang
 * @Description:
 **/

public class User {

    private String name;
    private String sex;
    private String address;
    private int age;

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, String sex, String address, int age) {
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.age = age;
    }

    public User(String name, String sex, String address) {
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
