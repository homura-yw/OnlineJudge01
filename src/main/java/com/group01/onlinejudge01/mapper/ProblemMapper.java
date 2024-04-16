package com.group01.onlinejudge01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.Problem;

import java.util.List;

@Mapper
public interface ProblemMapper  extends BaseMapper<Problem> {
    @Select("SELECT * FROM problem_table WHERE problemID = #{problemID}")
    Problem getProblemById(int problemID);

    @Select("SELECT * FROM problem_table")
    List<Problem> getAll();

    @Insert("INSERT INTO problem_table (problemID, problemName, problemDescription, problemDifficulty, " +
            "problemType, testCaseID, creationDate, creator, timeLimit, memoryLimit, additionalTestCases) " +
            "VALUES (#{problemID}, #{problemName}, #{problemDescription}, #{problemDifficulty}, #{problemType}, " +
            "#{testCaseID}, #{creationDate}, #{creator}, #{timeLimit}, #{memoryLimit}, #{additionalTestCases})")
    int insertProblem(Problem problem);

    @Update("UPDATE problem_table SET problemName = #{problemName}, problemDescription = #{problemDescription}, " +
            "problemDifficulty = #{problemDifficulty}, problemType = #{problemType}, testCaseID = #{testCaseID}, " +
            "creationDate = #{creationDate}, creator = #{creator}, timeLimit = #{timeLimit}, " +
            "memoryLimit = #{memoryLimit}, additionalTestCases = #{additionalTestCases} " +
            "WHERE problemID = #{problemID}")
    int updateProblem(Problem problem);

    @Delete("DELETE FROM problem_table WHERE problemID = #{problemID}")
    int deleteProblem(int problemID);
}
