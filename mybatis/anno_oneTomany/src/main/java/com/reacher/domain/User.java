package com.reacher.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String UserAddress;
    private String UserSex;
    private Date UserBirthday;

    //一对多关系映射： 一个用户对应多个账户
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + userName + '\'' +
                ", address='" + UserAddress + '\'' +
                ", sex='" + UserSex + '\'' +
                ", birthday=" + UserBirthday +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        this.UserAddress = userAddress;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        this.UserSex = userSex;
    }

    public Date getUserBirthday() {
        return UserBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.UserBirthday = userBirthday;
    }
}
