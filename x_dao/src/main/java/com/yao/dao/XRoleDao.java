package com.yao.dao;

import com.yao.bean.pojo.XRolePojo;

import java.util.List;
public interface XRoleDao {
    public int insertRecord(XRolePojo record);

    public XRolePojo getRecordByKey(XRolePojo record);

    public XRolePojo getRecordByWhere(XRolePojo record);

    public List<XRolePojo> getRecordListByWhere(XRolePojo record);

    public List<Long> getIdsListByWhere(XRolePojo record);

    public int updateRecordByKey(XRolePojo record);

    public int deleteRecordByKey(XRolePojo record);

}
