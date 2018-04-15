package com.gangster.cms.admin.dto;

import java.util.Date;
import java.util.List;

public class InformationObject {
    private String userName;
    private String userPhone;
    private String userEmail;
    private Integer userStatus;
    private Date userCreateTime;
    private String userOrg;
    private List<String> userGroup;


    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public InformationObject() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public List<String> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(List<String> userGroup) {
        this.userGroup = userGroup;
    }

    public InformationObject(String userName, String userPhone, String userEmail, Integer userStatus, Date userCreateTime, String userOrg, List<String> userGroup) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.userCreateTime = userCreateTime;
        this.userOrg = userOrg;
        this.userGroup = userGroup;
    }
}
