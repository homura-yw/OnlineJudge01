package com.group01.onlinejudge01.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group01.onlinejudge01.mapper.TestCaseMapper;
import com.group01.onlinejudge01.pojo.Problem;
import com.group01.onlinejudge01.mapper.ProblemMapper;
import com.group01.onlinejudge01.pojo.TestCase;
import com.group01.onlinejudge01.pojo.query.ProblemQuery;
import com.group01.onlinejudge01.pojo.query.ProblemVO;
import com.group01.onlinejudge01.pojo.vo.PageVO;
import com.group01.onlinejudge01.pojo.vo.ProblemDetailVO;
import com.group01.onlinejudge01.service.IProblemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Override
    public PageVO<ProblemVO> queryProblem(ProblemQuery query) {
        //1 构建查询条件
        Long pageSize = query.getPageSize();
        Long pageNo = query.getPageNo();


        Page<Problem> page = Page.of(pageNo, pageSize);
        //TODO 修改默认查询 的排序 条件
//        page.addOrder(new OrderItem());
        //2 分页查询
        lambdaQuery().eq(query.getProblemType() != null, Problem::getProblemType,query.getProblemType())
                .eq(query.getProblemDifficulty() != null , Problem::getProblemDifficulty, query.getProblemDifficulty())
                .page(page);

        //3 封装
        PageVO<ProblemVO> result = new PageVO<>();
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());

        List<Problem> records = page.getRecords();
        //如果没有数据
        if(CollUtil.isEmpty(records)){
            result.setList(Collections.emptyList());
            return result;
        }
        //有数据,拷贝problem到VO
        List<ProblemVO> problemVOList = BeanUtil.copyToList(records, ProblemVO.class);
        result.setList(problemVOList);

        return result;
    }

    @Override
    public ProblemDetailVO getDetail(Integer id) {
        //1  查询problem表，获取字段并赋值给VO
        Problem problem = this.getById(id);
        ProblemDetailVO problemDetailVO = BeanUtil.copyProperties(problem, ProblemDetailVO.class);

        //2 获取题目对应的test，赋值给VO
        //获取题目的id,多表联查题目对应的test
        int problemId = problem.getProblemId();
        //创建wrapper查询语句
        LambdaQueryWrapper<TestCase> wrapper = new LambdaQueryWrapper<TestCase>().eq(TestCase::getProblemId, problemId);
        List<TestCase> testCaseList = testCaseMapper.selectList(wrapper);
        problemDetailVO.setTestCases(testCaseList);

        //3 返回
        return problemDetailVO;
    }
}
