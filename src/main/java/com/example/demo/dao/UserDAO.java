package com.example.demo.dao;

import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zuwy Ling
 * @description
 * @create 2019-07-01
 **/
@Mapper
@Repository
public interface UserDAO {

    @Select(value = "SELECT * FROM `user` WHERE wx_openid = #{_parameter} LIMIT 1")
    List<User> getUserByOpenId(String open_id);

    @Insert(value = "INSERT INTO `user`\n" +
            "(`nickname`,\n" +
            "`wx_openid`,\n" +
            "`avatar_url`,\n" +
            "`token`)\n" +
            "VALUES\n" +
            "(#{nickname},\n" +
            "#{wx_openid},\n" +
            "#{avatar_url},\n" +
            "#{token});")
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    void insert(User user);

    @Update(value = "UPDATE `user`\n" +
            "SET\n" +
            "`nickname` = #{nickname},\n" +
            "`avatar_url` = #{avatar_url},\n" +
            "`token` = #{token}\n" +
            "WHERE `user_id` = ${user_id};")
    int update(User user);
}