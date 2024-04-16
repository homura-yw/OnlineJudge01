package com.group01.onlinejudge01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import com.group01.onlinejudge01.pojo.TestCase;

@Mapper
public interface TestCaseMapper extends BaseMapper<TestCase> {
    @Select("SELECT * FROM test_case_table WHERE testCaseID = #{testCaseID}")
    TestCase getTestCaseById(int testCaseID);

    @Insert("INSERT INTO test_case_table (testCaseID, problemID, inputDescription, outputDescription, " +
            "sampleInput, sampleOutput) " +
            "VALUES (#{testCaseID}, #{problemID}, #{inputDescription}, #{outputDescription}, " +
            "#{sampleInput}, #{sampleOutput})")
    int insertTestCase(TestCase testCase);

    @Update("UPDATE test_case_table SET problemID = #{problemID}, inputDescription = #{inputDescription}, " +
            "outputDescription = #{outputDescription}, sampleInput = #{sampleInput}, " +
            "sampleOutput = #{sampleOutput} " +
            "WHERE testCaseID = #{testCaseID}")
    int updateTestCase(TestCase testCase);

    @Delete("DELETE FROM test_case_table WHERE testCaseID = #{testCaseID}")
    int deleteTestCase(int testCaseID);
}
