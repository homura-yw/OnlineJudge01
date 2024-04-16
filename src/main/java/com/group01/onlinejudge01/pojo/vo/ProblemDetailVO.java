package com.group01.onlinejudge01.pojo.vo;

import com.group01.onlinejudge01.pojo.TestCase;
import lombok.Data;

import java.util.List;

@Data
public class ProblemDetailVO {
    private int problemId;
    private String problemName;
    private String problemDescription;
    private int problemDifficulty;
    private int problemType;
    private int timeLimit;
    private int memoryLimit;
    private String additionalTestCases;
    List<TestCase> testCases;
}
