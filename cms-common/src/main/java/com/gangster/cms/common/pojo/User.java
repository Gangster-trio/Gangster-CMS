package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String userPhone;

    private String userPassword;

    private String userEmail;

    private Integer userStatus;

    private Date userCreateTime;

    private Date userLastLogin;

    private String userOrg;

    private Boolean userIsAdmin;

    private static final long serialVersionUID = 1L;

    public User(Integer userId, String userName, String userPhone, String userPassword, String userEmail, Integer userStatus, Date userCreateTime, Date userLastLogin, String userOrg, Boolean userIsAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.userCreateTime = userCreateTime;
        this.userLastLogin = userLastLogin;
        this.userOrg = userOrg;
        this.userIsAdmin = userIsAdmin;
    }

    public User() {
        super();
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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg == null ? null : userOrg.trim();
    }

    public Boolean getUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(Boolean userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPhone() == null ? other.getUserPhone() == null : this.getUserPhone().equals(other.getUserPhone()))
            && (this.getUserPassword() == null ? other.getUserPassword() == null : this.getUserPassword().equals(other.getUserPassword()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getUserCreateTime() == null ? other.getUserCreateTime() == null : this.getUserCreateTime().equals(other.getUserCreateTime()))
            && (this.getUserLastLogin() == null ? other.getUserLastLogin() == null : this.getUserLastLogin().equals(other.getUserLastLogin()))
            && (this.getUserOrg() == null ? other.getUserOrg() == null : this.getUserOrg().equals(other.getUserOrg()))
            && (this.getUserIsAdmin() == null ? other.getUserIsAdmin() == null : this.getUserIsAdmin().equals(other.getUserIsAdmin()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPhone() == null) ? 0 : getUserPhone().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getUserCreateTime() == null) ? 0 : getUserCreateTime().hashCode());
        result = prime * result + ((getUserLastLogin() == null) ? 0 : getUserLastLogin().hashCode());
        result = prime * result + ((getUserOrg() == null) ? 0 : getUserOrg().hashCode());
        result = prime * result + ((getUserIsAdmin() == null) ? 0 : getUserIsAdmin().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userCreateTime=").append(userCreateTime);
        sb.append(", userLastLogin=").append(userLastLogin);
        sb.append(", userOrg=").append(userOrg);
        sb.append(", userIsAdmin=").append(userIsAdmin);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}