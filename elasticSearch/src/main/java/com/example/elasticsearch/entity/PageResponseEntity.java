package com.example.elasticsearch.entity;

import java.util.List;

/**
 * @ProjectName elasticsearchtest
 * @PackageName com.itboy.elasticsearchtest.entity
 * @ClassName PageResponseEntity
 * @Author zhanggeyang
 * @Date 2021-08-08 12:11
 * @Description
 * @Version 1.0
 */

public class PageResponseEntity<T> {
    private Long total;
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageResponseEntity() {
    }

    public PageResponseEntity(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }
}
