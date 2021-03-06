package com.yao.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 妖妖
 * @date 10:06 2021/3/6
 */


public class RoleTreeBean {
    private String id;                      //节点唯一索引值，用于对指定节点进行各类操作
    private String title;                   //节点标题
    private String field;                   //节点字段名
    private List<RoleTreeBean> children;    //子节点。支持设定选项同父节点
    private String href;                    //点击节点弹出新窗口对应的 url。需开启 isJump 参数
    private Boolean spread;                 //节点是否初始展开，默认 false
    private Boolean checked;                //节点是否初始为选中状态（如果开启复选框的话），默认 false
    private Boolean disabled;               //节点是否为禁用状态。默认 false

    public String getId() {
        return id;
    }

    public RoleTreeBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RoleTreeBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getField() {
        return field;
    }

    public RoleTreeBean setField(String field) {
        this.field = field;
        return this;
    }

    public List<RoleTreeBean> getChildren() {
        if (children == null)
            children = new ArrayList<>();
        return children;
    }

    public RoleTreeBean setChildren(List<RoleTreeBean> children) {
        this.children = children;
        return this;
    }

    public String getHref() {
        return href;
    }

    public RoleTreeBean setHref(String href) {
        this.href = href;
        return this;
    }

    public Boolean getSpread() {
        return spread;
    }

    public RoleTreeBean setSpread(Boolean spread) {
        this.spread = spread;
        return this;
    }

    public Boolean getChecked() {
        return checked;
    }

    public RoleTreeBean setChecked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public RoleTreeBean setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
