package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.GroupDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.dao.MissionDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Group;
import com.example.demo.entity.Member;
import com.example.demo.entity.Mission;
import com.example.demo.entity.User;
import com.example.demo.http.GroupResponse;
import com.example.demo.service.ListService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MissionDAO missionDAO;

    /**
     * 登陆
     * @param nickname
     * @param avatar_url
     * @param auth_code
     * @return
     */
    @Override
    public User loginByAuthCode(String nickname, String avatar_url, String auth_code) {

        Wx_app wx_app = new Wx_app();
        String appid = wx_app.getAppid();
        String appsecret = wx_app.getAppsecret();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+auth_code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        String session_key = jsonObject.get("session_key")+"";
        String openid = jsonObject.get("openid")+"";
        List<User> users = userDAO.getUserByOpenId(openid);
        boolean shouldCreate = users.isEmpty();
        User user = shouldCreate ? new User() : users.get(0);
        user.setNickname(nickname);
        user.setAvatar_url(avatar_url);
        user.setWx_openid(openid);
        user.setToken(openid+session_key);
        if(shouldCreate){
            userDAO.insert(user);
        }else{
            userDAO.update(user);
        }
        return user;
    }

    /**
     * 提供用户ID，获得用户可访问的所有群组
     * @param id
     * @return 所有用户可访问的群组，包括作为管理员和作为用户
     */
    @Override
    public List<GroupResponse> getGroupsForUser(int id) {
        List<Group> userList = groupDAO.getGroupForUserFromUserId(id);
        List<Group> adminList = groupDAO.getGroupForAdminFromUserId(id);
        List<GroupResponse> result = new ArrayList<>();
        for (Group g: userList){
            convertGroup(result, g, false);
        }
        for(Group g: adminList){
            convertGroup(result, g, true);
        }
        return result;
    }

    /**
     * 解码GroupResponse的内容
     * @param result
     * @param g
     * @param b
     */
    private void convertGroup(List<GroupResponse> result, Group g, boolean b) {
        result.add(convertGroup(g,b));
    }
    public GroupResponse convertGroup(Group g, boolean b) {
        GroupResponse gr = new GroupResponse();
        gr.setGroup_id(g.getGroup_id());
        gr.setGroup_desc(g.getGroup_desc());
        gr.setGroup_name(g.getGroup_name());
        gr.setGroup_type(g.getGroup_type());
        gr.setIs_admin(b);
        GroupResponse.GroupMissionStatus m = gr.new GroupMissionStatus();
        m.setAll_count(0);
        m.setFinished_count(0);
        m.setPersonal_finished_count(0);
        gr.setMission_info(m);
        gr.setUser_id(g.getUser_id());
        return gr;
    }

    /**
     *新建群组
     * @param group
     */
    @Override
    public void insertGroup(Group group) {
        groupDAO.insert(group);
    }

    /**
     * 更新群组
     * @param group
     * @return
     */
    @Override
    public int updateGroup(Group group) {
        groupDAO.update(group);
        return 0;
    }

    /**
     * 用户加入群组
     * @param member
     * @return
     */
    @Override
    public int insertMember(Member member) {
        memberDAO.insert(member);
        return 0;
    }

    /**
     * 更新群组用户名单（目前用不上）
     * @param member
     * @return
     */
    @Override
    public int updateMember(Member member) {
        memberDAO.update(member);
        return 0;
    }

    /**
     * 获取组内用户信息
     * @param id
     * @return
     */
    @Override
    public List<Member> getMemberByGroupId(int id) {
        memberDAO.getMemberByGroupId(id);
        return null;
    }

    /**
     * 通过user_id获取group_id
     * @param id
     * @return
     */
    @Override
    public Group getGroupById(int id){
        return groupDAO.getGroupById(id);
    }

    /**
     * 插入任务数据到数据库
     * @param mission
     */
    @Override
    public void insertByMissionID(Mission mission) {
        missionDAO.insertByMissionID(mission);
    }

    /**
     * 删除某一任务
     * @param mission
     */
    @Override
    public void deleteByMissionID(Mission mission) {
        missionDAO.deleteByMissionID(mission);
    }

    /**
     * 删除全部已完成任务
     * @param group_id
     */
    @Override
    public void deleteAllDone(int group_id) {
        missionDAO.deleteAllDone(group_id);
    }

    /**
     * 选择任务
     * @param user_id
     * @param group_id
     * @return
     */
    @Override
    public List<Mission> selectByID(int user_id, int group_id) {
        List<Mission> mission = missionDAO.selectByID(user_id,group_id);
        return mission;
    }

    /**
     * 获取member_id
     * @param user_id
     * @param group_id
     * @return
     */
    @Override
    public int getMemberID(int user_id, int group_id) {
        int member_id = memberDAO.getMemberID(user_id,group_id);
        return member_id;
    }

    /**
     * 获取用户信息，历史遗留
     * @param open_id
     * @return
     */
    @Override
    public List<User> getUserByOpenId(String open_id) {
        return null;
    }

    /**
     * 插入用户，历史遗留
     * @param user
     */
    @Override
    public void insert(User user) {

    }

    /**
     * 更改用户信息，历史遗留
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        return 0;
    }

    /**
     * 历史遗留
     * @param id
     * @return
     */
    @Override
    public List<Group> getGroupForUserFromUserId(int id) {
        return null;
    }

    /**
     * 历史遗留
     * @param id
     * @return
     */
    @Override
    public List<Group> getGroupForAdminFromUserId(int id) {
        return null;
    }
}