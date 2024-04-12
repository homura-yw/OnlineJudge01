package com.group01.onlinejudge01.mapper;

import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.User;

@Mapper
public interface userMapper {
    @Select("SELECT * FROM user_table WHERE user_id = #{userId}")
    User getUserById(int userId);

    @Insert("INSERT INTO user_table (password, nickname, img_url, num_submission, num_solved, regist_date) " +
            "VALUES (#{password}, #{nickname}, #{imgUrl}, #{numSubmission}, #{numSolved}, #{registDate})")
    int insertUser(User user);

    @Update("UPDATE user_table SET password = #{password}, nickname = #{nickname}, img_url = #{imgUrl}, " +
            "num_submission = #{numSubmission}, num_solved = #{numSolved}, regist_date = #{registDate} " +
            "WHERE user_id = #{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM user_table WHERE user_id = #{userId}")
    int deleteUser(int userId);
}
