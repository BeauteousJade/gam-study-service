package com.pby.gamstudy.bean;

import java.util.List;

public class DailyTask {
    private boolean isSign;
    private List<Card> dailyTask;
    private int dailyTaskCount;

    public DailyTask(boolean isSign, List<Card> dailyTask, int dailyTaskCount) {
        this.isSign = isSign;
        this.dailyTask = dailyTask;
        this.dailyTaskCount = dailyTaskCount;
    }

    public int getDailyTaskCount() {
        return dailyTaskCount;
    }

    public void setDailyTaskCount(int dailyTaskCount) {
        this.dailyTaskCount = dailyTaskCount;
    }

    public boolean isSign() {
        return isSign;
    }

    public void setSign(boolean sign) {
        isSign = sign;
    }

    public List<Card> getDailyTask() {
        return dailyTask;
    }

    public void setDailyTask(List<Card> dailyTask) {
        this.dailyTask = dailyTask;
    }
}
