package com.yao.dao;

import com.yao.bean.pojo.XServicePojo;

import java.util.List;
public interface XServiceDao {
    public int insertRecord(XServicePojo record);

    public XServicePojo getRecordByKey(XServicePojo record);

    public XServicePojo getRecordByWhere(XServicePojo record);

    public List<XServicePojo> getRecordListByWhere(XServicePojo record);

    public int updateRecordByKey(XServicePojo record);

    public int deleteRecordByKey(XServicePojo record);

}
