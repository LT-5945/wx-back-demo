package com.example.demo.entity;

import lombok.Data;

/**
 * @description
 * @author Zuwy Ling
 * @create 2019-07-01
 **/
@Data
public class Member {

    private Integer memeber_id;
    private Integer user_id;
    private Integer group_id;

    public Integer getMemeber_id() {
        return memeber_id;
    }

    public void setMemeber_id(Integer memeber_id) {
        this.memeber_id = memeber_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}