package com.group01.onlinejudge01.controller;

import com.group01.onlinejudge01.judgeService.judgeService;
import com.group01.onlinejudge01.pojo.JudgeRequest;
import com.group01.onlinejudge01.pojo.submission;
import org.springframework.web.bind.annotation.*;

@RestController
public class testContrller {
    private judgeService judgeservice;

    public testContrller(judgeService judgeservice) {
        this.judgeservice = judgeservice;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world!! 114514！！";
    }

    //codeUrl,用户的cpp文件
    //testUrl,OSS下题目的位置
    //0/1
    @PostMapping("/send")
    public String sendTest(
            @RequestParam("codeUrl") String codeUrl,
            @RequestParam("subtestNum") Integer subtestNum,
            @RequestParam("memoryLimit") Integer memoryLimit,
            @RequestParam("timeLimit") Integer timeLimit,
            @RequestParam("isContest") Integer isContest,
            @RequestParam("problemType") Integer problemType,
            @RequestParam("submitId") String submitId,
            @RequestParam("testUrl") String testUrl
    )
    {
        judgeservice.send(
                new JudgeRequest(
                        codeUrl,
                        submitId,
                        testUrl,
                        subtestNum,
                        memoryLimit,
                        timeLimit,
                        isContest,
                        problemType
                )
        );
        return "ok";
    }

    @GetMapping("/getbyid")
    public submission getStateById(String id) throws Exception {
        return judgeservice.getSubmissionById(id);
    }
}
