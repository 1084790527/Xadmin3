package com.yao.bean.model;
/**
 * @author 妖妖
 * @date 15:37 2021/3/15
 */

import com.yao.bean.pojo.XSystemLogPojo;

public class XSystemLogModel extends XSystemLogPojo {

    private String managerName;

    public String getManagerName() {
        return managerName;
    }

    public XSystemLogModel setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }

    public XSystemLogModel(XSystemLogPojo pojo) {
        setTableName(pojo.getTableName());
        setId(pojo.getId());
        setRequestUrl(pojo.getRequestUrl());
        setMethod(pojo.getMethod());
        setIp(pojo.getIp());
        setClassMethod(pojo.getClassMethod());
        setArgs(pojo.getArgs());
        setCreDate(pojo.getCreDate());
        setManagerId(pojo.getManagerId());
        setType(pojo.getType());
    }
    public XSystemLogModel() {

    }
}
