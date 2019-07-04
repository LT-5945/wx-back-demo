package com.example.demo.service;


import com.example.demo.entity.Group;
import com.example.demo.entity.Member;
import com.example.demo.entity.Mission;
import com.example.demo.entity.User;
import com.example.demo.http.GroupResponse;
import com.example.demo.service.impl.PersenalMissionStatus;

import java.util.List;

/**
 * @author Zuwy Ling
 * @description
 * @create 2019-07-01
 **/
public interface ListService {

    //UserDAO
    List<User> getUserByOpenId(String open_id);
    void insert(User user);
    int update(User user);
    //User but not DAO
    User loginByAuthCode (String nickname, String avatar_url, String auth_code);


    //GroupDAO
    List<Group> getGroupForUserFromUserId(int id);
    List<Group> getGroupForAdminFromUserId(int id);
    Group getGroupById(int id);
    void insertGroup(Group group);
    int updateGroup(Group group);

    //MemberDAO
    int insertMember (Member member);
    int updateMember (Member member);
    List<Member> getMemberByGroupId(int id);
    int getMemberID(int user_id, int group_id);

    //MisssionDAO
    void insertByMissionID(Mission mission,Integer member_id);
    void deleteByMissionID(Mission mission);
    void deleteAllDone(int group_id);
    List<Mission> selectByID(int user_id,int group_id);
    List<PersenalMissionStatus> selectPnDoneNumImpl(int group_id);
    int selectAllNum(int group_id);
    int selectDoneNum(int group_id);
    List<Integer> selectMemberALL(int group_id);

    //methods not DAO
    List<GroupResponse> getGroupsForUser(int id);
    GroupResponse convertGroup(Group g, boolean b);



}