package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XManagerPojo {
    private String id;
    private String serviceId;
    private String roleId;
    private String nickname;
    private String mobileNo;
    private String password;
    private Date regDate;
    private String state;
    private Date updateDate;
    private Date lastLoginDate;
    private String lastOperId;
    private Date lastOperDate;
    private String lastLoginIp;
    private String defaults;
    public String getId() {
        return id;
    }
    public XManagerPojo setId(String id) {
        this.id = id;
        return this;
    }

    public String getServiceId() {
        return serviceId;
    }
    public XManagerPojo setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }
    public XManagerPojo setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }
    public XManagerPojo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }
    public XManagerPojo setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public XManagerPojo setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getRegDate() {
        return regDate;
    }
    public XManagerPojo setRegDate(Date regDate) {
        this.regDate = regDate;
        return this;
    }

    public String getState() {
        return state;
    }
    public XManagerPojo setState(String state) {
        this.state = state;
        return this;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    public XManagerPojo setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }
    public XManagerPojo setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public String getLastOperId() {
        return lastOperId;
    }
    public XManagerPojo setLastOperId(String lastOperId) {
        this.lastOperId = lastOperId;
        return this;
    }

    public Date getLastOperDate() {
        return lastOperDate;
    }
    public XManagerPojo setLastOperDate(Date lastOperDate) {
        this.lastOperDate = lastOperDate;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }
    public XManagerPojo setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getDefaults() {
        return defaults;
    }
    public XManagerPojo setDefaults(String defaults) {
        this.defaults = defaults;
        return this;
    }

}
