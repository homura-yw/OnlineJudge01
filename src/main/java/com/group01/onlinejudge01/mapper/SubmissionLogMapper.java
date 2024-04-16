package com.group01.onlinejudge01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.SubmissionLog;

@Mapper
public interface SubmissionLogMapper extends BaseMapper<SubmissionLog> {
    @Select("SELECT * FROM submission_log WHERE submissionID = #{submissionID}")
    SubmissionLog getSubmissionLogById(int submissionID);

    @Insert("INSERT INTO submission_log (submissionID, userID, problemID, codeUrl) " +
            "VALUES (#{submissionID}, #{userID}, #{problemID}, #{codeUrl})")
    int insertSubmissionLog(SubmissionLog submissionLog);

    @Update("UPDATE submission_log SET userID = #{userID}, problemID = #{problemID}, codeUrl = #{codeUrl} " +
            "WHERE submissionID = #{submissionID}")
    int updateSubmissionLog(SubmissionLog submissionLog);

    @Delete("DELETE FROM submission_log WHERE submissionID = #{submissionID}")
    int deleteSubmissionLog(int submissionID);
}
