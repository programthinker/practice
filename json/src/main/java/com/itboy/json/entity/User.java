package com.itboy.json.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName practice
 * @PackageName com.itboy.json.entity
 * @ClassName User
 * @Author zhanggeyang
 * @Date 2022-01-26 17:51
 * @Description
 * @Version 1.0
 */

@Data
public class User implements Serializable {
    private String name;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
    private Home home;
    private List<String> codes;

    public User(String name, String address, LocalDateTime birthday,Home home,List<String> codes) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.home = home;
        this.codes = codes;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", home=" + home +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                address.equals(user.address) &&
                birthday.equals(user.birthday) &&
                home.equals(user.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, birthday, home);
    }


}
