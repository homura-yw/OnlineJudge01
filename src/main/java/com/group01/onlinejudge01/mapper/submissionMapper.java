package com.group01.onlinejudge01.mapper;

import com.group01.onlinejudge01.pojo.submission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface submissionMapper {

    @Select("select * from submissions where id = #{id}")
    List<submission> getItemById(String id);

    @Insert("insert into submissions(id, time, verdict, pass_num) " +
            "values(#{id}, #{time}, #{verdict}, #{pass_num})")
    int insertItem(String id, String time, String verdict, Integer pass_num);
}
