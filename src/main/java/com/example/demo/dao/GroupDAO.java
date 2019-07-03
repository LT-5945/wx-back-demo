package com.example.demo.dao;

import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
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
public interface GroupDAO {

    @Select(value = "SELECT `group`.* FROM `group` WHERE (group_id IN (SELECT group_id FROM member WHERE user_id = #{_id}))")
    List<Group> getGroupForUserFromUserId(int id);

    @Select(value = "SELECT `group`.* FROM `group` WHERE user_id = #{_parameter}")
    List<Group> getGroupForAdminFromUserId(int id);

    @Select(value = "SELECT `group`.* FROM `group` WHERE group_id = #{_parameter} LIMIT 1")
    Group getGroupById(int id);

    @Insert(value = "INSERT INTO `group`\n" +
            "(`group_name`,\n" +
            "`group_desc`,\n" +
            "`user_id`,\n" +
            "`group_type`)\n" +
            "VALUES\n" +
            "(#{group_name},\n" +
            "#{group_desc},\n" +
            "#{user_id},\n" +
            "#{group_type})\n")
    @Options(useGeneratedKeys = true, keyProperty = "group_id", keyColumn = "group_id")
    void insert(Group group);

    @Update(value = "UPDATE `group` SET group_name = #{group_name}, group_desc = #{group_desc}, group_type = ${group_type} WHERE group_id = #{group_id}")
    int update(Group group);
}