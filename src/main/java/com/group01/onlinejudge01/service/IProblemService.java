package com.group01.onlinejudge01.service;

import com.group01.onlinejudge01.pojo.Problem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group01.onlinejudge01.pojo.query.PageQuery;
import com.group01.onlinejudge01.pojo.query.ProblemQuery;
import com.group01.onlinejudge01.pojo.query.ProblemVO;
import com.group01.onlinejudge01.pojo.vo.PageVO;
import com.group01.onlinejudge01.pojo.vo.ProblemDetailVO;


public interface IProblemService extends IService<Problem> {

    PageVO<ProblemVO> queryProblem(ProblemQuery query);

    ProblemDetailVO getDetail(Integer id);
}
