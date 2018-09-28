package com.tiaotiaopoker.entity;

/**
 * Created by xiekang on 2018/9/25.
 */
public class WithdrawLimit {
    private int   todayWithdrawTimes;
    private float todayWithdrawTotalAmount;

    public Integer getTodayWithdrawTimes() {
        return todayWithdrawTimes;
    }

    public void setTodayWithdrawTimes(Integer todayWithdrawTimes) {
        this.todayWithdrawTimes = todayWithdrawTimes;
    }


    public float getTodayWithdrawTotalAmount() {
        return todayWithdrawTotalAmount;
    }

    public void setTodayWithdrawTotalAmount(float todayWithdrawTotalAmount) {
        this.todayWithdrawTotalAmount = todayWithdrawTotalAmount;
    }
}
