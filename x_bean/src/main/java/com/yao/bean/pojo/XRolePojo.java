package com.yao.bean.pojo;

import java.util.Date;
import java.util.List;

public class XRolePojo {
    private Long id;
    private List<Long> ids;
    private String name;
    private Long serviceId;
    private String description;
    private String state;
    private List<String> inState;
    private String defaults;
    private Long creOperId;
    private Date creOperDate;
    private Long lastOperId;
    private Date lastOperDate;

    public List<String> getInState() {
        return inState;
    }

    public XRolePojo setInState(List<String> inState) {
        this.inState = inState;
        return this;
    }

    public List<Long> getIds() {
        return ids;
    }

    public XRolePojo setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }

    public Long getId() {
        return id;
    }
    public XRolePojo setId(Long id) {
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

    public Long getServiceId() {
        return serviceId;
    }
    public XRolePojo setServiceId(Long serviceId) {
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

    public Long getCreOperId() {
        return creOperId;
    }
    public XRolePojo setCreOperId(Long creOperId) {
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

    public Long getLastOperId() {
        return lastOperId;
    }
    public XRolePojo setLastOperId(Long lastOperId) {
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
