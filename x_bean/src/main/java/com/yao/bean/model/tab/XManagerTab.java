package com.yao.bean.model.tab;
/**
 * @author 妖妖
 * @date 15:07 2021/3/6
 */

import com.yao.bean.pojo.XManagerPojo;

public class XManagerTab extends XManagerPojo {
    private int page;
    private int limit;
    private String sort;
    private String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
