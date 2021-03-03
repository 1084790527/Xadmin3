package com.yao.dao;

import com.yao.bean.pojo.XManagerPojo;

import java.util.List;
public interface XManagerDao {
    public int insertRecord(XManagerPojo record);

    public XManagerPojo getRecordByKey(XManagerPojo record);

    public XManagerPojo getRecordByWhere(XManagerPojo record);

    public List<XManagerPojo> getRecordListByWhere(XManagerPojo record);

    public int updateRecordByKey(XManagerPojo record);

    public int deleteRecordByKey(XManagerPojo record);

}
