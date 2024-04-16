package com.group01.onlinejudge01.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("submission_log")
public class SubmissionLog {
    @TableId(value = "submissionID", type = IdType.AUTO)
    private Integer submissionID;
    private Integer userId;
    private Integer problemId;
    private String codeUrl;
}
