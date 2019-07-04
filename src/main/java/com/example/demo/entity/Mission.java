package com.example.demo.entity;

import lombok.Data;

/**
 * @description
 * @author Zuwy Ling
 * @create 2019-07-01
 **/
@Data
public class Mission {

    private Integer mission_id;
    private String mission_name;
    private String mission_desc;
    private Integer member_id;

    public Integer getMission_id() {
        return mission_id;
    }

    public void setMission_id(Integer mission_id) {
        this.mission_id = mission_id;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getMission_desc() {
        return mission_desc;
    }

    public void setMission_desc(String mission_desc) {
        this.mission_desc = mission_desc;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }
}