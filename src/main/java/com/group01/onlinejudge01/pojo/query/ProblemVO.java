package com.group01.onlinejudge01.pojo.query;

import lombok.Data;

@Data
public class ProblemVO {
    private Integer problemId;
    private String problemName;
    private Integer problemDifficulty;
    private Integer problemType;
}
