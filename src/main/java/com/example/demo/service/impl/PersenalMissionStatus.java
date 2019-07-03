package com.example.demo.service.impl;

public class PersenalMissionStatus {
    private int user_id;
    private int missionDoneNum;

    public PersenalMissionStatus(int user_id, int missionDoneNum) {
        this.user_id = user_id;
        this.missionDoneNum = missionDoneNum;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMissionDoneNum() {
        return missionDoneNum;
    }

    public void setMissionDoneNum(int missionDoneNum) {
        this.missionDoneNum = missionDoneNum;
    }
}
