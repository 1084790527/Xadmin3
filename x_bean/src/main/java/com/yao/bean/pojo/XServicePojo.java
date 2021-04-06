package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XServicePojo {
    private Long id;
    private String name;
    private String realName;
    private String mobileNo;
    private String state;
    private List<String> inState;
    private Date regDate;
    private Long creOperId;
    private Date creOperDate;
    private Long lastOperId;
    private Date lastOperDate;

    public List<String> getInState() {
        return inState;
    }

    public XServicePojo setInState(List<String> inState) {
        this.inState = inState;
        return this;
    }

    public Long getId() {
        return id;
    }
    public XServicePojo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public XServicePojo setName(String name) {
        this.name = name;
        return this;
    }

    public String getRealName() {
        return realName;
    }
    public XServicePojo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }
    public XServicePojo setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public String getState() {
        return state;
    }
    public XServicePojo setState(String state) {
        this.state = state;
        return this;
    }

    public Date getRegDate() {
        return regDate;
    }
    public XServicePojo setRegDate(Date regDate) {
        this.regDate = regDate;
        return this;
    }

    public Long getCreOperId() {
        return creOperId;
    }
    public XServicePojo setCreOperId(Long creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public XServicePojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

    public Long getLastOperId() {
        return lastOperId;
    }
    public XServicePojo setLastOperId(Long lastOperId) {
        this.lastOperId = lastOperId;
        return this;
    }

    public Date getLastOperDate() {
        return lastOperDate;
    }
    public XServicePojo setLastOperDate(Date lastOperDate) {
        this.lastOperDate = lastOperDate;
        return this;
    }

}
