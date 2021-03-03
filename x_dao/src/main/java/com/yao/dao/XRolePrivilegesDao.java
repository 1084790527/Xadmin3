package com.yao.dao;

import com.yao.bean.pojo.XRolePrivilegesPojo;

import java.util.List;
public interface XRolePrivilegesDao {
    public int insertRecord(XRolePrivilegesPojo record);

    public XRolePrivilegesPojo getRecordByKey(XRolePrivilegesPojo record);

    public XRolePrivilegesPojo getRecordByWhere(XRolePrivilegesPojo record);

    public List<XRolePrivilegesPojo> getRecordListByWhere(XRolePrivilegesPojo record);

    public int updateRecordByKey(XRolePrivilegesPojo record);

    public int deleteRecordByKey(XRolePrivilegesPojo record);

}
