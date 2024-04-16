package com.group01.onlinejudge01.pojo.query;

import lombok.Data;

@Data
public class ProblemQuery extends PageQuery{
    //问题难度
    private Integer problemDifficulty;
    //问题类型
    private Integer problemType;
}
