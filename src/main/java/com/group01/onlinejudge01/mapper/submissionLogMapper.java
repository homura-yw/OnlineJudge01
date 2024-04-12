package com.group01.onlinejudge01.mapper;

import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.SubmissionLog;

@Mapper
public interface submissionLogMapper {
    @Select("SELECT * FROM submission_logs WHERE submissionID = #{submissionID}")
    SubmissionLog getSubmissionLogById(int submissionID);

    @Insert("INSERT INTO submission_logs (submissionID, userID, problemID, codeUrl) " +
            "VALUES (#{submissionID}, #{userID}, #{problemID}, #{codeUrl})")
    int insertSubmissionLog(SubmissionLog submissionLog);

    @Update("UPDATE submission_logs SET userID = #{userID}, problemID = #{problemID}, codeUrl = #{codeUrl} " +
            "WHERE submissionID = #{submissionID}")
    int updateSubmissionLog(SubmissionLog submissionLog);

    @Delete("DELETE FROM submission_logs WHERE submissionID = #{submissionID}")
    int deleteSubmissionLog(int submissionID);
}
