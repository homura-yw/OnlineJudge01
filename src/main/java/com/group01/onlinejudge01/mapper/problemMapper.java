package com.group01.onlinejudge01.mapper;

import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.Problem;

@Mapper
public interface problemMapper {
    @Select("SELECT * FROM problem_table WHERE problemID = #{problemID}")
    Problem getProblemById(int problemID);

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
