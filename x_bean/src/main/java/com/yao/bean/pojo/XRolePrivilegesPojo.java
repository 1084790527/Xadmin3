package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XRolePrivilegesPojo {
    private String roleId;
    private String privilegesId;
    private String creOperId;
    private Date creOperDate;
    public String getRoleId() {
        return roleId;
    }
    public XRolePrivilegesPojo setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getPrivilegesId() {
        return privilegesId;
    }
    public XRolePrivilegesPojo setPrivilegesId(String privilegesId) {
        this.privilegesId = privilegesId;
        return this;
    }

    public String getCreOperId() {
        return creOperId;
    }
    public XRolePrivilegesPojo setCreOperId(String creOperId) {
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
