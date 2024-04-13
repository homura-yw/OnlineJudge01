package com.group01.onlinejudge01.mapper;

import org.apache.ibatis.annotations.*;

import com.group01.onlinejudge01.pojo.User;

@Mapper
public interface userMapper {
    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User getUserById(int userId);

    @Select("select *from users where nickname=#{nickname}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            // 其他字段映射
    })
    User getUserByNickName(String nickname);

    @Insert("INSERT INTO users (password, nickname, img_url, num_submission, num_solved, regist_date) " +
            "VALUES (#{password}, #{nickname}, #{imgUrl}, #{numSubmission}, #{numSolved}, #{registDate})")
    int insertUser(User user);

    @Update("UPDATE users SET password = #{password}, nickname = #{nickname}, img_url = #{imgUrl}, " +
            "num_submission = #{numSubmission}, num_solved = #{numSolved}, regist_date = #{registDate} " +
            "WHERE user_id = #{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    int deleteUser(int userId);
}
