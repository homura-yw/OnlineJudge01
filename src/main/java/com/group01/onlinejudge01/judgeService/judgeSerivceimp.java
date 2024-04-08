package com.group01.onlinejudge01.judgeService;

import com.group01.onlinejudge01.mapper.submissionMapper;
import com.group01.onlinejudge01.pojo.JudgeRequest;
import com.group01.onlinejudge01.pojo.submission;
import com.group01.onlinejudge01.utils.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class judgeSerivceimp implements judgeService{
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                25,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new CustomThreadFactory()
        );
    private submissionMapper submissionmapper;
    public void send(JudgeRequest judgeRequest) {
        threadPoolExecutor.execute(new JudgeTask(judgeRequest, JudgeLinkUtil.URL, submissionmapper));
    }

    public submission getSubmissionById(String id) throws Exception {
        submission submissionItem;
        if(isRunning.contain(id)) {
            Jedis jedis = JedisLinkPool.getJedis();
            Integer res = 0;
            Integer passedNum = 0;
            String timeStr = "";
            if(jedis.exists(id)) {
                res = Integer.parseInt(jedis.get(id));
                passedNum = Integer.parseInt(jedis.get(id + "num"));
                timeStr = jedis.get(id + "time");
            }
            else {
                res = -1;
                timeStr = jedis.get(id + "time");
            }
            String verdict;
            if(res == -1) verdict = "In queue";
            else if(res == 0) verdict = "Accept";
            else if(res == 1) verdict = "Wrong answer";
            else if(res == 2) verdict = "Time limit exceeded";
            else if(res == 3) verdict = "memory limit exceeded";
            else verdict = "Compilation error";
            submissionItem = new submission(id, timeStr, verdict, passedNum);
            jedis.close();
        } else {
            List<submission> submissionList = submissionmapper.getItemById(id);
            for(submission sub: submissionList) {
                System.out.println(sub.toString());
            }
            if(submissionList.size() == 0) {
                throw new Exception();
            }
            submissionItem = submissionList.get(0);
        }
        return submissionItem;
    }
}
