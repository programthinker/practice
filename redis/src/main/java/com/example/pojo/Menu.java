package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName practice
 * @PackageName entity
 * @ClassName Menu
 * @Author zhanggeyang
 * @Date 2022-05-06 21:36
 * @Description
 * @Version 1.0
 */

public class Menu {

    private Integer id;
    private Integer pid;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creatTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id.equals(menu.id) &&
                pid.equals(menu.pid) &&
                name.equals(menu.name) &&
                creatTime.equals(menu.creatTime) &&
                childList.equals(menu.childList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, name, creatTime, childList);
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public Menu(Integer id, Integer pid, String name, LocalDateTime creatTime, List<Menu> childList) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.creatTime = creatTime;
        this.childList = childList;
    }

    private List<Menu> childList;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", creatTime=" + creatTime +
                ", childList=" + childList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }

    public Menu() {
    }

    public Menu(Integer id, String name, Integer pid, LocalDateTime creatTime) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.creatTime = creatTime;
    }

    public Menu(Integer id, String name, Integer pid, List<Menu> childList, LocalDateTime creatTime) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.childList = childList;
        this.creatTime = creatTime;
    }
}
