package com.example.demo.controller;

import com.example.demo.entity.*;

import com.example.demo.http.GroupResponse;
import com.example.demo.http.LoginRequest;
import com.example.demo.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author Yao Pian
 * @create 2019-07-01
 **/
@RestController
public class ListController {
    @Autowired
    private ListService listService;
    //login 登陆
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public Map<String,Object> login (@RequestBody LoginRequest request) {
        User user = listService.loginByAuthCode(request.getNickname(),request.getAvatar_url(),request.getAuth_code());
        HashMap<String,Object> result = new HashMap<>();
        result.put("user_id",user.getUser_id());
        result.put("token",user.getToken());

        return result;
    }

    //createGroup 创建群组
    @RequestMapping(value = "/createGroup",method = RequestMethod.POST)
    public GroupResponse insert(@RequestBody Group group, @RequestParam(name="user_id", defaultValue="") int token){
        group.setUser_id(token);
        listService.insertGroup(group);
        return listService.convertGroup(group,true);
    }

    //updateGroup 编辑群组
    @RequestMapping(value = "/updateGroup",method = RequestMethod.POST)
    public GroupResponse update(@RequestBody Group group, @RequestParam(name="user_id", defaultValue="") int token){
        group.setUser_id(token);
        listService.updateGroup(group);
        return listService.convertGroup(group,true);
    }

//    //deleteGroup  删除群组
//    @RequestMapping(value = "/deleteGroup",method = RequestMethod.GET)
//    public Group delete(@RequestBody Group group, @RequestParam(name="user_id", defaultValue="") int token){
//        listService.
//        return group;
//    }

//    //getMembersForGroup 查找成员(管理员权限)
//    @RequestMapping(value = "/getMembersForGroup",method = RequestMethod.GET)
//    public Group getMembersForGroup(@RequestBody int id, @RequestParam(name="user_id", defaultValue="") int token){
//        List<Member> Members = listService.getMemberByGroupId();
//        return ;
//    }

    //getGroups 查找群组(用户)
    @RequestMapping(value = "/getGroups",method = RequestMethod.GET)
    public List<GroupResponse> search(@RequestParam(name="user_id", defaultValue="") int token){
        List<GroupResponse> groups = listService.getGroupsForUser(token);
        return groups;
    }
    //joinGroupFlow  加入群组/1
    @RequestMapping(value = "/joinGroupFlow",method = RequestMethod.POST)
    public Group joinGroupFlow (@RequestBody Member member, @RequestParam(name="user_id", defaultValue="") int token){
        return listService.getGroupById(member.getGroup_id());
    }

    //joinGroupConfirm  加入群组/1
    @RequestMapping(value = "/joinGroupConfirm",method = RequestMethod.POST)
    public GroupResponse joinGroupConfirm (@RequestBody Member member, @RequestParam(name="user_id", defaultValue="") int token){
        member.setUser_id(token);
        listService.insertMember(member);
        return listService.convertGroup(listService.getGroupById(member.getGroup_id()),false);
    }





//    URL/createMission【创建任务】
    @RequestMapping(value = "/createMission", method = RequestMethod.POST)
    public Mission insertMission(@RequestBody Mission mission, @RequestParam(name="user_id", defaultValue="") int token){
        listService.insertByMissionID(mission,mission.getMember_id());
        return mission;
    }
//    URL/updateMission【编辑任务】
//    URL/deleteMission【删除任务】
//    URL/getMissionssForUser【查找任务（成员）】
//    URL/getTasksForGroup【查找任务（管理员）】
//    URL/completeMission【完成任务（用户）】
//    URL/rejectMission【驳回任务（管理员）】
}