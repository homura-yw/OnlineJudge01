package com.group01.onlinejudge01.judgeService;

import com.group01.onlinejudge01.pojo.JudgeRequest;
import com.group01.onlinejudge01.utils.CustomThreadFactory;
import com.group01.onlinejudge01.utils.JudgeLinkUtil;
import com.group01.onlinejudge01.utils.JudgeTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class judgeSerivceimp implements judgeService{
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
            25,
            10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new CustomThreadFactory()
        );

    public void send(JudgeRequest judgeRequest) {
        threadPoolExecutor.execute(new JudgeTask(judgeRequest, JudgeLinkUtil.URL));
    }
}
