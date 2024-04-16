package com.group01.onlinejudge01.controller;


import com.group01.onlinejudge01.judgeService.judgeService;
import com.group01.onlinejudge01.pojo.JudgeRequest;
import com.group01.onlinejudge01.pojo.Result;
import com.group01.onlinejudge01.pojo.query.ProblemQuery;
import com.group01.onlinejudge01.pojo.query.ProblemVO;
import com.group01.onlinejudge01.pojo.submission;
import com.group01.onlinejudge01.pojo.vo.PageVO;
import com.group01.onlinejudge01.pojo.vo.ProblemDetailVO;
import com.group01.onlinejudge01.service.impl.ProblemServiceImpl;
import com.group01.onlinejudge01.service.impl.TestCaseServiceImpl;
import com.group01.onlinejudge01.utils.OSSutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("problem")
public class ProblemController {
    @Autowired
    private judgeService judgeservice;

    @Autowired
    private OSSutil ossUtil;


    @Autowired
    ProblemServiceImpl problemService;

    @Autowired
    TestCaseServiceImpl testCaseService;


    /***
     * 分页获取题目
     * @param query 分页参数
     * @return
     */
    @GetMapping("page")
    public PageVO<ProblemVO> queryProblemPages(ProblemQuery query){
        return problemService.queryProblem(query);
    }


    /***
     * 根据id获取单个题目的详细信息
     * @param id 题目的id
     * @return
     */
    @GetMapping()
    public Result getById(Integer id){
        ProblemDetailVO problemDetailVO = problemService.getDetail(id);
        return Result.success(problemDetailVO);
    }

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public Result upload(MultipartFile file) throws IOException {
        String url = ossUtil.upload(file);
        return Result.success(url);
    }

    /**
     * 判题
     * @param codeUrl
     * @param subtestNum
     * @param memoryLimit
     * @param timeLimit
     * @param isContest
     * @param problemType
     * @param submitId
     * @param testUrl
     * @return
     */
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

    /**
     * 获得判题结果
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/getbyid")
    public submission getStateById(String submitID) throws Exception {
        return judgeservice.getSubmissionById(submitID);
    }

}
