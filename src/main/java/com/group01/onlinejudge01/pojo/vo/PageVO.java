package com.group01.onlinejudge01.pojo.vo;

import lombok.Data;

import java.util.List;


@Data
public class PageVO<T> {
    private Long total;
    private Long pages;
    private List<T> list;
}
