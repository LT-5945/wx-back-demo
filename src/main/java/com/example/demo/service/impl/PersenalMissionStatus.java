package com.example.demo.service.impl;

public class PersenalMissionStatus {
    private int member_id;//7.4由user_id改为member_id
    private int missionDoneNum;

    public PersenalMissionStatus(int member_id, int missionDoneNum) {
        this.member_id = member_id;
        this.missionDoneNum = missionDoneNum;
    }

    public int getUser_id() {
        return member_id;
    }

    public void setUser_id(int member_id) {
        this.member_id = member_id;
    }

    public int getMissionDoneNum() {
        return missionDoneNum;
    }

    public void setMissionDoneNum(int missionDoneNum) {
        this.missionDoneNum = missionDoneNum;
    }
}
