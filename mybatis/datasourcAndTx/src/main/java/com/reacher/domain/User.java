package com.reacher.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer UserId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;

    @Override
    public String toString() {
        return "User{" +
                "id=" + UserId +
                ", username='" + userName + '\'' +
                ", address='" + userAddress + '\'' +
                ", sex='" + userSex + '\'' +
                ", birthday=" + userBirthday +
                '}';
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        this.UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getBirthday(Date date) {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }
}
