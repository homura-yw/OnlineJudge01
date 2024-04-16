package com.group01.onlinejudge01.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Value;

@TableName("problem_table")
@Data
public class Problem {
    @TableId
    private int problemId;
    private String problemName;
    private String problemDescription;
    private int problemDifficulty;
    private int problemType;
    private int testCaseId;
    private String creationDate;
    private String creator;
    private int timeLimit;
    private int memoryLimit;
    private String additionalTestCases;

}
