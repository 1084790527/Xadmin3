package com.yao.dao;

import com.yao.bean.pojo.XManagerRolePojo;

import java.util.List;
public interface XManagerRoleDao {
    public int insertRecord(XManagerRolePojo record);

    public XManagerRolePojo getRecordByKey(XManagerRolePojo record);

    public XManagerRolePojo getRecordByWhere(XManagerRolePojo record);

    public List<XManagerRolePojo> getRecordListByWhere(XManagerRolePojo record);

    public List<String> getRoleIdListByWhere(XManagerRolePojo record);

    public int updateRecordByKey(XManagerRolePojo record);

    public int deleteRecordByKey(XManagerRolePojo record);


}
