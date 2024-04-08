package com.group01.onlinejudge01.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class submission {
    private String id;
    private String time;
    private String verdict;
    private Integer pass_num;
}
