package com.yao.bean.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;

public class XManagerRolePojo {
    @JsonSerialize(using=ToStringSerializer.class)
    private Long managerId;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long roleId;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long creOperId;
    private Date creOperDate;
    public Long getManagerId() {
        return managerId;
    }
    public XManagerRolePojo setManagerId(Long managerId) {
        this.managerId = managerId;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }
    public XManagerRolePojo setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getCreOperId() {
        return creOperId;
    }
    public XManagerRolePojo setCreOperId(Long creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public XManagerRolePojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

}
