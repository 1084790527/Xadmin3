package com.yao.bean.model;

import com.yao.bean.pojo.XServicePojo;

import java.util.List;

/**
 * @author 妖妖
 * @date 11:19 2021/3/5
 */


public class XServiceModel extends XServicePojo {

    private String creOperName;
    private String lastOperName;
    private String password;
    private List<Long> roleIds;
    private List<XRoleModel> roles;

    public List<XRoleModel> getRoles() {
        return roles;
    }

    public XServiceModel setRoles(List<XRoleModel> roles) {
        this.roles = roles;
        return this;
    }

    public XServiceModel() {
    }

    public XServiceModel(XServicePojo pojo) {
        setId(pojo.getId());
        setName(pojo.getName());
        setRealName(pojo.getRealName());
        setMobileNo(pojo.getMobileNo());
        setState(pojo.getState());
        setRegDate(pojo.getRegDate());
        setCreOperId(pojo.getCreOperId());
        setCreOperDate(pojo.getCreOperDate());
        setLastOperId(pojo.getLastOperId());
        setLastOperDate(pojo.getLastOperDate());
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public XServiceModel setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public XServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCreOperName() {
        return creOperName;
    }

    public XServiceModel setCreOperName(String creOperName) {
        this.creOperName = creOperName;
        return this;
    }

    public String getLastOperName() {
        return lastOperName;
    }

    public XServiceModel setLastOperName(String lastOperName) {
        this.lastOperName = lastOperName;
        return this;
    }
}
