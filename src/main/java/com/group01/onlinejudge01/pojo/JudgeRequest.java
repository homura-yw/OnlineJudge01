package com.group01.onlinejudge01.pojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JudgeRequest {
    private String codeUrl;
    private String submitId;
    private String testUrl;
    private Integer subtestNum;
    private Integer memoryLimit;
    private Integer timeLimit;
    private Integer isContest;
    private Integer problemType;

    public JudgeRequest(String codeUrl,
                        String submitId,
                        String testUrl,
                        Integer subtestNum,
                        Integer memoryLimit,
                        Integer timeLimit,
                        Integer isContest,
                        Integer problemType) {
        this.codeUrl = codeUrl;
        this.submitId = submitId;
        this.testUrl = testUrl;
        this.subtestNum = subtestNum;
        this.memoryLimit = memoryLimit;
        this.timeLimit = timeLimit;
        this.isContest = isContest;
        this.problemType = problemType;
    }
}
