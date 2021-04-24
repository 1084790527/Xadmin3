package com.yao.bean.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;

public class XManagerPojo {
    @JsonSerialize(using=ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long serviceId;
    private String nickname;
    private String mobileNo;
    private String password;
    private Date regDate;
    private String state;
    private List<String> inState;
    private Date updateDate;
    private Date lastLoginDate;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long lastOperId;
    private Date lastOperDate;
    private String lastLoginIp;
    private String defaults;

    public List<String> getInState() {
        return inState;
    }

    public XManagerPojo setInState(List<String> inState) {
        this.inState = inState;
        return this;
    }

    public Long getId() {
        return id;
    }
    public XManagerPojo setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getServiceId() {
        return serviceId;
    }
    public XManagerPojo setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public Long getLastOperId() {
        return lastOperId;
    }
    public XManagerPojo setLastOperId(Long lastOperId) {
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
