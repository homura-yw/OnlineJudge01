package com.group01.onlinejudge01.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group01.onlinejudge01.judgeService.judgeService;
import com.group01.onlinejudge01.mapper.SubmissionLogMapper;
import com.group01.onlinejudge01.pojo.*;
import com.group01.onlinejudge01.pojo.query.ProblemQuery;
import com.group01.onlinejudge01.pojo.query.ProblemVO;
import com.group01.onlinejudge01.pojo.vo.PageVO;
import com.group01.onlinejudge01.pojo.vo.ProblemDetailVO;
import com.group01.onlinejudge01.service.impl.ProblemServiceImpl;
import com.group01.onlinejudge01.service.impl.SubmissionLogServiceImpl;
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
@CrossOrigin
public class ProblemController {
    @Autowired
    private judgeService judgeservice;

    @Autowired
    private OSSutil ossUtil;


    @Autowired
    ProblemServiceImpl problemService;

    @Autowired
    TestCaseServiceImpl testCaseService;

    @Autowired
    SubmissionLogServiceImpl submissionLogService;



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
     * 文件上传,返回用户代码的OSS链接
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
     * 逻辑：前端发送题目ID,用户ID,和用户上传的代码文件路径。通过这三项数据，可以填写submission_log表
     * submission_log表自动生成submissionID.再调用草佬的接口，把生成的submissionID传入(原来计划是传入每个人的QQ号）
     * 对于testUrl(题目的OSS链接）,也不用传，可以通过题目ID获取
     * 总结：相较于草佬的测试代码，该代码参数增加了用户ID,题目ID。少了testUrl,submitID。
     * @return ok
     */
    @PostMapping("/send")
    public String sendTest(
            @RequestParam("userId") Integer userId,
            @RequestParam("problemId") Integer problemId,
            @RequestParam("codeUrl") String codeUrl,
            @RequestParam("subtestNum") Integer subtestNum,
            @RequestParam("memoryLimit") Integer memoryLimit,
            @RequestParam("timeLimit") Integer timeLimit,
            @RequestParam("isContest") Integer isContest,
            @RequestParam("problemType") Integer problemType
    )
    {
        //填写submissionLog表
        SubmissionLog submissionLog = new SubmissionLog();
        submissionLog.setUserId(userId);
        submissionLog.setProblemId(problemId);
        submissionLog.setCodeUrl(codeUrl);
        submissionLogService.save(submissionLog);

        //获取题目的OSS链接，也就是testUrl
        Problem problem = problemService.getById(problemId);
        String testUrl = problem.getOssUrl();

        //获取submissionLog表生成的id,用来代替submitID
        QueryWrapper<SubmissionLog> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SubmissionLog::getCodeUrl,codeUrl);
        SubmissionLog one = submissionLogService.getOne(wrapper);
//        System.out.println(one.getSubmissionID());
        String submitId =Integer.toString(one.getSubmissionID());
//        System.out.println(submitId);
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
     * @param codeUrl 用户的代码OSS链接
     * @return
     * @throws Exception
     */
    @GetMapping("answer")
    public submission getAnswer(String codeUrl) throws Exception {
        LambdaQueryWrapper<SubmissionLog> wrapper = new LambdaQueryWrapper<SubmissionLog>()
                .eq(SubmissionLog::getCodeUrl, codeUrl);
        SubmissionLog one = submissionLogService.getOne(wrapper);
        String submitID = String.valueOf(one.getSubmissionID());
        return judgeservice.getSubmissionById(submitID);
    }


}
