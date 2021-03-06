package com.yao.bean.model;

import com.yao.bean.pojo.XServicePojo;

/**
 * @author 妖妖
 * @date 11:19 2021/3/5
 */


public class XServiceModel extends XServicePojo {

    private String creOperName;
    private String lastOperName;

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
