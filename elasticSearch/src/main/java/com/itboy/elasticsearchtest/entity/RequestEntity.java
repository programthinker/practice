package com.itboy.elasticsearchtest.entity;

/**
 * @ProjectName elasticsearchtest
 * @PackageName com.itboy.elasticsearchtest.entity
 * @ClassName RequestEntity
 * @Author zhanggeyang
 * @Date 2021-08-08 12:10
 * @Description
 * @Version 1.0
 */

public class RequestEntity {
    private String name;
    private String sex;
    private String address;
    private int age;
    private String max;
    private String min;
    private int currentPage;
    private int pageSize;
    private String sortOrder;

    public RequestEntity(String name, String sex, String address, int age, String max, String min, int currentPage, int pageSize,String sortOrder) {
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.max = max;
        this.min = min;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }


    public RequestEntity() {
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
}
