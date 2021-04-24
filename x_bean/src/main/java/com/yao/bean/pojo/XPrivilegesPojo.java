package com.yao.bean.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

public class XPrivilegesPojo {
    @JsonSerialize(using=ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using=ToStringSerializer.class)
    private List<Long> ids;
    private String name;
    private String description;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long parentId;
    @JsonSerialize(using=ToStringSerializer.class)
    private Long menuLevel;
    private String permission;
    private Long permissionType;
    private String url;
    private String param;
    private String methodType;
    private String state;
    private List<XPrivilegesPojo> xPrivileges;

    public List<Long> getIds() {
        return ids;
    }

    public XPrivilegesPojo setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }

    public List<XPrivilegesPojo> getxPrivileges() {
        return xPrivileges;
    }

    public XPrivilegesPojo setxPrivileges(List<XPrivilegesPojo> xPrivileges) {
        this.xPrivileges = xPrivileges;
        return this;
    }

    public Long getId() {
        return id;
    }
    public XPrivilegesPojo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public XPrivilegesPojo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public XPrivilegesPojo setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }
    public XPrivilegesPojo setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getMenuLevel() {
        return menuLevel;
    }
    public XPrivilegesPojo setMenuLevel(Long menuLevel) {
        this.menuLevel = menuLevel;
        return this;
    }

    public String getPermission() {
        return permission;
    }
    public XPrivilegesPojo setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Long getPermissionType() {
        return permissionType;
    }
    public XPrivilegesPojo setPermissionType(Long permissionType) {
        this.permissionType = permissionType;
        return this;
    }

    public String getUrl() {
        return url;
    }
    public XPrivilegesPojo setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getParam() {
        return param;
    }
    public XPrivilegesPojo setParam(String param) {
        this.param = param;
        return this;
    }

    public String getMethodType() {
        return methodType;
    }
    public XPrivilegesPojo setMethodType(String methodType) {
        this.methodType = methodType;
        return this;
    }

    public String getState() {
        return state;
    }
    public XPrivilegesPojo setState(String state) {
        this.state = state;
        return this;
    }

}
