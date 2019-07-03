package com.example.demo.dao;

import com.example.demo.entity.Mission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Zuwy Ling
 * @description Mission的数据库持久层，与数据库内的任务数据进行操作
 * @Functions
 *  按MissionID增加
 *  按MissionID删除
 *  删除已完成的任务
 *  按group_id & user_id查询
 *  查询总任务数
 *  查询个人已完成任务数
 *  查询群组已完成任务数
 *  查询群组全部任务数
 *  查询群组内成员数量
 * @create 2019-07-03
 **/
@Mapper
@Repository
public interface MissionDAO {

    // 按MissionID增加
    @Insert(value = "INSERT INTO `mission`\n" +
            "(`mission_name`,\n" +
            "`mission_desc`,\n" +
            "`member_id`,\n" +
            "`finish_type`)\n" +
            "VALUES\n" +
            "(#{mission_name},\n" +
            "#{mission_desc},\n" +
            "#{member_id},\n" +
            "#{mission_type})\n")
    @Options(useGeneratedKeys = true, keyProperty = "mission_id", keyColumn = "mission_id")
    void insertByMissionID(Mission mission);

    //按MissionID删除
    @Delete(value = "DELETE FROM `mission`" +
            "WHERE (`mission_id` \n" +
            " = #{mission_id})")
    void deleteByMissionID(Mission mission);

    //删除已完成的任务
    @Delete(value = "DELETE FROM `mission` WHERE member_id IN (SELECT member_id FROM `member` WHERE group_id = #{_parameter}) AND mission_type = 1")
    void deleteAllDone(int group_id);

    //按group_id & user_id查询
    @Select(value = "SELECT `mission`.* FROM `mission` WHERE `member_id` IN (SELECT `member_id` FROM `member` WHERE user_id = #{0} AND group_id = #{1})")
    List<Mission> selectByID(int user_id,int group_id);

    //查询个人已完成任务数
    @Select(value = "SELECT count(*) FROM `mission` WHERE `member_id` IN (SELECT `member_id` FROM `member` WHERE user_id = #{0} AND group_id = #{1})")
    int selectPnDoneNum(int user_id, int group_id);

    //查询群组已完成任务数
    @Select(value = "SELECT count(*) FROM `mission` WHERE `member_id` IN (SELECT `member_id` FROM `member` WHERE group_id = #{0}) AND mission_type = 1")
    int selectDoneNum(int group_id);

    //查询群组全部任务数
    @Select(value = "SELECT count(*) FROM `mission` WHERE `member_id` IN (SELECT `member_id` FROM `member` WHERE group_id = #{0})")
    int selectAllNum(int group_id);

    //查询群组内成员组成,返回所有的member_id
    @Select(value = "SELECT count(*) FROM `member` WHERE `member_id` IN (SELECT `member_id` FROM `member` WHERE `group_id` = #{0}) GROUP BY `member_id`")
    List<Integer> selectMemberALL(int group_id);
}
