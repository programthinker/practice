package com.itboy.json.entity;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName practice
 * @PackageName com.itboy.json.entity
 * @ClassName Home
 * @Author zhanggeyang
 * @Date 2023-04-06 16:29
 * @Description
 * @Version 1.0
 */
@Data
public class Home {
    private String size;
    private String address;
    private List<String> codes;

    public Home() {
    }

    public Home(String size, String address,List<String> codes) {
        this.size = size;
        this.address = address;
        this.codes = codes;
    }
}
