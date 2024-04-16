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
    private Integer problemId;
    private String problemName;
    private String problemDescription;
    private Integer problemDifficulty;
    private Integer problemType;
    private Integer testCaseId;
    private String creationDate;
    private String creator;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String additionalTestCases;
    private String OssUrl;

}
