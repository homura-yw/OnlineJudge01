package com.group01.onlinejudge01.judgeService;

import com.group01.onlinejudge01.pojo.JudgeRequest;
import com.group01.onlinejudge01.pojo.submission;

public interface judgeService {
    void send(JudgeRequest judgeRequest);
    submission getSubmissionById(String id) throws Exception;
}
