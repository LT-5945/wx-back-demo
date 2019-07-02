package com.example.demo.http;

import lombok.Data;

@Data
public class GroupResponse {
    @Data
    public class GroupMissionStatus {
        private int personal_finished_count = 0;
        private int finished_count = 0;
        private int all_count = 0;

        public int getPersonal_finished_count() {
            return personal_finished_count;
        }

        public void setPersonal_finished_count(int personal_finished_count) {
            this.personal_finished_count = personal_finished_count;
        }

        public int getFinished_count() {
            return finished_count;
        }

        public void setFinished_count(int finished_count) {
            this.finished_count = finished_count;
        }

        public int getAll_count() {
            return all_count;
        }

        public void setAll_count(int all_count) {
            this.all_count = all_count;
        }
    }

    private Integer group_id;
    private String group_name;
    private String group_desc;
    private Integer user_id;
    private Integer group_type;
    private GroupMissionStatus mission_info;
    private boolean is_admin;

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

    public GroupMissionStatus getMission_info() {
        return mission_info;
    }

    public void setMission_info(GroupMissionStatus mission_info) {
        this.mission_info = mission_info;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}