package com.example.demo.service;


import com.example.demo.entity.Group;
import com.example.demo.entity.Member;
import com.example.demo.entity.User;
import com.example.demo.http.GroupResponse;

import java.util.List;

/**
 * @author Zuwy Ling
 * @description
 * @create 2019-07-01
 **/
public interface ListService {

    User loginByAuthCode (String nickname, String avatar_url, String auth_code);
    List<GroupResponse> getGroupsForUser(int id);
    void insertGroup(Group group);
    int updateGroup(Group group);
    int insertMember (Member member);
    int updateMember (Member member);
    Group getGroupById(int id);
    List<Member> getMemberByGroupId(int id);
    GroupResponse convertGroup(Group g, boolean b);

}