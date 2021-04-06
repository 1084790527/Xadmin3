package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XRolePrivilegesPojo {
    private Long roleId;
    private List<Long> roleIds;
    private Long privilegesId;
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
