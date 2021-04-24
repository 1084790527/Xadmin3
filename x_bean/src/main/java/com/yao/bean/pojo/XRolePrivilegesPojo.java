package com.yao.bean.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;

public class XRolePrivilegesPojo {
    @JsonSerialize(using=ToStringSerializer.class)
    private Long roleId;
    @JsonSerialize(using=ToStringSerializer.class)
    private List<Long> roleIds;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long privilegesId;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long creOperId;
    private Date creOperDate;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public XRolePrivilegesPojo setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public Long getRoleId() {
        return roleId;
    }
    public XRolePrivilegesPojo setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public Long getPrivilegesId() {
        return privilegesId;
    }
    public XRolePrivilegesPojo setPrivilegesId(Long privilegesId) {
        this.privilegesId = privilegesId;
        return this;
    }

    public Long getCreOperId() {
        return creOperId;
    }
    public XRolePrivilegesPojo setCreOperId(Long creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public XRolePrivilegesPojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

}
