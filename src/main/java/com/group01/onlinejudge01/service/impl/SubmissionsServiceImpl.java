package com.group01.onlinejudge01.service.impl;

import com.group01.onlinejudge01.pojo.submission;
import com.group01.onlinejudge01.mapper.submissionMapper;
import com.group01.onlinejudge01.service.ISubmissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class SubmissionsServiceImpl extends ServiceImpl<submissionMapper, submission> implements ISubmissionsService {

}
