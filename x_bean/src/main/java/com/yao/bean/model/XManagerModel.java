package com.yao.bean.model;
/**
 * @author 妖妖
 * @date 15:03 2021/3/6
 */

import com.yao.bean.pojo.XManagerPojo;

import java.util.List;


public class XManagerModel extends XManagerPojo {

    private String lastOperName;
    private List<String> roleIds;
    private List<XRoleModel> roles;

    private String oldPwd;
    private String newPwd;

    public XManagerModel() {

    }

    public XManagerModel(XManagerPojo pojo) {
        setId(pojo.getId());
        setServiceId(pojo.getServiceId());
        setNickname(pojo.getNickname());
        setMobileNo(pojo.getMobileNo());
        setRegDate(pojo.getRegDate());
        setState(pojo.getState());
        setUpdateDate(pojo.getUpdateDate());
        setLastLoginDate(pojo.getLastLoginDate());
        setLastOperId(pojo.getLastOperId());
        setLastOperDate(pojo.getLastOperDate());
        setLastLoginIp(pojo.getLastLoginIp());
        setDefaults(pojo.getDefaults());
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public XManagerModel setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
        return this;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public XManagerModel setNewPwd(String newPwd) {
        this.newPwd = newPwd;
        return this;
    }

    public List<XRoleModel> getRoles() {
        return roles;
    }

    public XManagerModel setRoles(List<XRoleModel> roles) {
        this.roles = roles;
        return this;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public XManagerModel setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public String getLastOperName() {
        return lastOperName;
    }

    public XManagerModel setLastOperName(String lastOperName) {
        this.lastOperName = lastOperName;
        return this;
    }
}
