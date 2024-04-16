package com.group01.onlinejudge01.pojo.query;

import lombok.Data;

@Data
public class PageQuery {
    private Long pageNo;
    private Long pageSize;
    private String sortBy;
    private Boolean isAsc;
}
