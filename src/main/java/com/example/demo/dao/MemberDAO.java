package com.example.demo.dao;

import com.example.demo.entity.Group;
import com.example.demo.entity.Member;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description
 * @author Zuwy Ling
 * @create 2019-07-01
 **/
@Mapper
@Repository
public interface MemberDAO {

    @Insert(value = "INSERT INTO `member`\n" +
            "(`member_id`,\n" +
            "`user_id`,\n" +
            "`group_id`)\n" +
            "VALUES\n" +
            "#{user_id},\n" +
            "#{group_id})")
    @Options(useGeneratedKeys = true, keyProperty = "group_id", keyColumn="group_id")
    int insert (Member member);

    @Update(value="UPDATE `member`\n" +
            "SET\n" +
            "`user_id` = #{user_id},\n" +
            "`group_id` = #{group_id}\n" +
            "WHERE `member_id` = #{member_id};\n")
    int update (Member member);

    @Select(value="SELECT * FROM `member` WHERE group_id = #{_parameter}")
    List<Member> getMemberByGroupId(int id);
}