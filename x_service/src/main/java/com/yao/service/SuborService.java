package com.yao.service;
/**
 * @author 妖妖
 * @date 11:23 2021/3/5
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yao.bean.model.XServiceModel;
import com.yao.bean.model.tab.XServiceTab;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.pojo.XServicePojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.util.Tool;
import com.yao.dao.XManagerDao;
import com.yao.dao.XServiceDao;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class SuborService {
    private static Log log = LogFactory.getLog(SuborService.class);

    @Autowired
    private XServiceDao xServiceDao;
    @Autowired
    private XManagerDao xManagerDao;


    public ResObj suborData(XServiceTab tab) {
        String id = tab.getId();
        String name = tab.getName();
        String realName = tab.getRealName();
        String mobileNo = tab.getMobileNo();
        String state = tab.getState();
        XServicePojo sel = new XServicePojo();
        if (StringUtils.isBlank(state))
            sel.setInState(Arrays.asList(new String[]{"1", "0"}));
        else
            sel.setState(state);
        if (StringUtils.isNotBlank(id))
            sel.setId(id);
        if (StringUtils.isNotBlank(name))
            sel.setName(name);
        if (StringUtils.isNotBlank(realName))
            sel.setRealName(realName);
        if (StringUtils.isNotBlank(mobileNo))
            sel.setMobileNo(mobileNo);

        if (StringUtils.isNotBlank(tab.getOrder()))
            PageHelper.orderBy(Tool.humpToLine(tab.getSort().replaceAll("Name","Id"))+" "+tab.getOrder());
        else
            PageHelper.orderBy("id desc");
        Page<XServicePojo> page = PageHelper.startPage(tab.getPage(),tab.getLimit(),true);
        xServiceDao.getRecordListByWhere(sel);
        List<XServiceModel> models = new ArrayList<>();
        for (XServicePojo pojo : page.getResult()) {
            XServiceModel model = new XServiceModel(pojo);
            String creOperId = model.getCreOperId();
            String lastOperId = model.getLastOperId();
            if (StringUtils.isNotBlank(creOperId)){
                model.setCreOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(creOperId)).getNickname());
            }
            if (StringUtils.isNotBlank(lastOperId)){
                model.setLastOperName(xManagerDao.getRecordByKey(new XManagerPojo().setId(lastOperId)).getNickname());
            }
            models.add(model);
        }
        return new ResObj().setState(true).setCount(page.getTotal()).setData(models);
    }

    public void suborState(XServiceModel model) {
        String id = model.getId();
        String state = model.getState();

    }

    public ResObj addData() {

        return new ResObj().setState(true);
    }
}
