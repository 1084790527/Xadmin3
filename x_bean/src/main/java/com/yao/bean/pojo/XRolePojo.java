package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XRolePojo {
    private String id;
    private List<String> ids;
    private String name;
    private String serviceId;
    private String description;
    private String state;
    private List<String> inState;
    private String defaults;
    private String creOperId;
    private Date creOperDate;
    private String lastOperId;
    private Date lastOperDate;

    public List<String> getInState() {
        return inState;
    }

    public XRolePojo setInState(List<String> inState) {
        this.inState = inState;
        return this;
    }

    public List<String> getIds() {
        return ids;
    }

    public XRolePojo setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    public String getId() {
        return id;
    }
    public XRolePojo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public XRolePojo setName(String name) {
        this.name = name;
        return this;
    }

    public String getServiceId() {
        return serviceId;
    }
    public XRolePojo setServiceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public XRolePojo setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }
    public XRolePojo setState(String state) {
        this.state = state;
        return this;
    }

    public String getDefaults() {
        return defaults;
    }
    public XRolePojo setDefaults(String defaults) {
        this.defaults = defaults;
        return this;
    }

    public String getCreOperId() {
        return creOperId;
    }
    public XRolePojo setCreOperId(String creOperId) {
        this.creOperId = creOperId;
        return this;
    }

    public Date getCreOperDate() {
        return creOperDate;
    }
    public XRolePojo setCreOperDate(Date creOperDate) {
        this.creOperDate = creOperDate;
        return this;
    }

    public String getLastOperId() {
        return lastOperId;
    }
    public XRolePojo setLastOperId(String lastOperId) {
        this.lastOperId = lastOperId;
        return this;
    }

    public Date getLastOperDate() {
        return lastOperDate;
    }
    public XRolePojo setLastOperDate(Date lastOperDate) {
        this.lastOperDate = lastOperDate;
        return this;
    }

}
