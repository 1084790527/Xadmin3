package com.yao.bean.vo;
/**
 * @author 妖妖
 * @date 14:14 2021/3/3
 */

public class ResObj {
    private String id;
    private boolean state;
    private int code;
    private String msg;
    private Long count;
    private Object data;

    public String getId() {
        return id;
    }

    public ResObj setId(String id) {
        this.id = id;
        return this;
    }

    public boolean isState() {
        return state;
    }

    public ResObj setState(boolean state) {
        this.state = state;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResObj setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResObj setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public ResObj setCount(Long count) {
        this.count = count;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResObj setData(Object data) {
        this.data = data;
        return this;
    }
}
