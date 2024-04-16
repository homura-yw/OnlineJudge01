package com.group01.onlinejudge01.service.impl;

import com.group01.onlinejudge01.pojo.SubmissionLog;
import com.group01.onlinejudge01.mapper.SubmissionLogMapper;
import com.group01.onlinejudge01.service.ISubmissionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class SubmissionLogServiceImpl extends ServiceImpl<SubmissionLogMapper, SubmissionLog> implements ISubmissionLogService {

}
