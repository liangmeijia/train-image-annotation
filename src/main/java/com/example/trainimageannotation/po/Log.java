package com.example.trainimageannotation.po;

import java.util.Date;

/**
 * @description 用户表DAO
 * @author LENOVO
 * @date 20230814
 */
public class Log {
    private Long logId;
    private String loginIp;
    private String userName;
    private int state;
    private Date createTime;

    public Log() {
    }

    public Long getId() {
        return logId;
    }

    public void setId(Long logId) {
        this.logId = logId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", loginIp='" + loginIp + '\'' +
                ", userName='" + userName + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
