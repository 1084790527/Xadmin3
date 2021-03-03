package com.yao.dao;

import com.yao.bean.pojo.XPrivilegesPojo;

import java.util.List;
public interface XPrivilegesDao {
    public int insertRecord(XPrivilegesPojo record);

    public XPrivilegesPojo getRecordByKey(XPrivilegesPojo record);

    public XPrivilegesPojo getRecordByWhere(XPrivilegesPojo record);

    public List<XPrivilegesPojo> getRecordListByWhere(XPrivilegesPojo record);

    public int updateRecordByKey(XPrivilegesPojo record);

    public int deleteRecordByKey(XPrivilegesPojo record);

}
