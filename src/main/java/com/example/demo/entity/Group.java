package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author Zuwy Ling
 * @create 2019-07-01
 **/

@Data
public class Group {

    private Integer group_id;
    private String group_name;
    private String group_desc;
    private Integer user_id;
    private Integer group_type;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_desc() {
        return group_desc;
    }

    public void setGroup_desc(String group_desc) {
        this.group_desc = group_desc;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGroup_type() {
        return group_type;
    }

    public void setGroup_type(Integer group_type) {
        this.group_type = group_type;
    }
}