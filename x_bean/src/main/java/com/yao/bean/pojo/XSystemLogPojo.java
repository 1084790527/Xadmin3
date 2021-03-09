package com.yao.bean.pojo;

import java.util.Date;

public class XSystemLogPojo {
    private String tableName;
    private String id;
    private String requestUrl;
    private String method;
    private String ip;
    private String classMethod;
    private String args;
    private Date creDate;
    private String managerId;
    private String type;

    public String getTableName() {
        return tableName;
    }

    public XSystemLogPojo setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getId() {
        return id;
    }
    public XSystemLogPojo setId(String id) {
        this.id = id;
        return this;
    }

    public String getRequestUrl() {
        return requestUrl;
    }
    public XSystemLogPojo setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public String getMethod() {
        return method;
    }
    public XSystemLogPojo setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getIp() {
        return ip;
    }
    public XSystemLogPojo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getClassMethod() {
        return classMethod;
    }
    public XSystemLogPojo setClassMethod(String classMethod) {
        this.classMethod = classMethod;
        return this;
    }

    public String getArgs() {
        return args;
    }
    public XSystemLogPojo setArgs(String args) {
        this.args = args;
        return this;
    }

    public Date getCreDate() {
        return creDate;
    }
    public XSystemLogPojo setCreDate(Date creDate) {
        this.creDate = creDate;
        return this;
    }

    public String getManagerId() {
        return managerId;
    }
    public XSystemLogPojo setManagerId(String managerId) {
        this.managerId = managerId;
        return this;
    }

    public String getType() {
        return type;
    }
    public XSystemLogPojo setType(String type) {
        this.type = type;
        return this;
    }

}
