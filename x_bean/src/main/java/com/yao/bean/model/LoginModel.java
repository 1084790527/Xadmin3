package com.yao.bean.model;
/**
 * @author 妖妖
 * @date 14:16 2021/3/3
 */


public class LoginModel {
    private String machineCode;
    private String mobileNo;
    private String password;


    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
