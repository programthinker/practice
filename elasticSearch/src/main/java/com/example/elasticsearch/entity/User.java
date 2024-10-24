package com.example.elasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

/**
 * @ProjectName: elasticsearchtest
 * @PackageName: com.itboy.elasticsearchtest.entity
 * @ClassName: User
 * @Date: 2021年08月05日 17:58
 * @Author: zhanggeyang
 * @Description:
 **/

@Document(indexName = "user")
public class User {
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String sex;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Integer)
    private int age;

    public User(Long id, String name, String sex, String address, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.age = age;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
