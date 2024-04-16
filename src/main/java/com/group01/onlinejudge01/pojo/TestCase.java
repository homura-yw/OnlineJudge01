package com.group01.onlinejudge01.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test_case_table")
public class TestCase {
    @TableId("test_case_id")
    private int testCaseId;
    private int problemId;
    private String inputDescription;
    private String outputDescription;
    private String sampleInput;
    private String sampleOutput;
}
