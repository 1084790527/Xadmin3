package com.yao.bean.model;
/**
 * @author 妖妖
 * @date 14:36 2021/3/5
 */

import com.yao.bean.RoleTreeBean;
import com.yao.bean.pojo.XRolePojo;

import java.util.List;


public class XRoleModel extends XRolePojo {

    private String creOperName;
    private String lastOperName;
    private List<String> priIds;
    private List<RoleTreeBean> roleTrees;
    private Boolean checked;

    public XRoleModel() {

    }
    public XRoleModel(XRolePojo pojo) {
        setId(pojo.getId());
        setName(pojo.getName());
        setServiceId(pojo.getServiceId());
        setDescription(pojo.getDescription());
        setState(pojo.getState());
        setDefaults(pojo.getDefaults());
        setCreOperId(pojo.getCreOperId());
        setCreOperDate(pojo.getCreOperDate());
        setLastOperId(pojo.getLastOperId());
        setLastOperDate(pojo.getLastOperDate());
    }

    public Boolean getChecked() {
        return checked;
    }

    public XRoleModel setChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public List<RoleTreeBean> getRoleTrees() {
        return roleTrees;
    }

    public XRoleModel setRoleTrees(List<RoleTreeBean> roleTrees) {
        this.roleTrees = roleTrees;
        return this;
    }

    public List<String> getPriIds() {
        return priIds;
    }

    public XRoleModel setPriIds(List<String> priIds) {
        this.priIds = priIds;
        return this;
    }

    public String getCreOperName() {
        return creOperName;
    }

    public XRoleModel setCreOperName(String creOperName) {
        this.creOperName = creOperName;
        return this;
    }

    public String getLastOperName() {
        return lastOperName;
    }

    public XRoleModel setLastOperName(String lastOperName) {
        this.lastOperName = lastOperName;
        return this;
    }
}
