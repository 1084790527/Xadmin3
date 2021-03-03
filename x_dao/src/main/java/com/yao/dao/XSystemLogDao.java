package com.yao.dao;

import com.yao.bean.pojo.XSystemLogPojo;

import java.util.List;
public interface XSystemLogDao {
    public int insertRecord(XSystemLogPojo record);

    public XSystemLogPojo getRecordByKey(XSystemLogPojo record);

    public XSystemLogPojo getRecordByWhere(XSystemLogPojo record);

    public List<XSystemLogPojo> getRecordListByWhere(XSystemLogPojo record);

    public int updateRecordByKey(XSystemLogPojo record);

    public int deleteRecordByKey(XSystemLogPojo record);

}
