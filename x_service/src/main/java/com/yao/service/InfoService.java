package com.yao.service;
/**
 * @author 妖妖
 * @date 16:16 2021/3/9
 */

import com.yao.bean.model.XManagerModel;
import com.yao.bean.pojo.XManagerPojo;
import com.yao.bean.vo.ResObj;
import com.yao.common.util.MD5Util;
import com.yao.dao.XManagerDao;
import com.yao.service.exception.CustException;
import com.yao.service.session.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class InfoService {
    private static Log log = LogFactory.getLog(InfoService.class);

    @Autowired
    private XManagerDao xManagerDao;

    public ResObj infoData() {
        XManagerPojo manager = xManagerDao.getRecordByKey(new XManagerPojo().setId(LoginUser.getId()));
        return new ResObj().setState(true).setData(new XManagerModel(manager));
    }

    public void info(XManagerModel model) {
        String nickname = model.getNickname();
        if (StringUtils.isBlank(nickname))
            throw new CustException("更改的昵称不能空");
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(LoginUser.getId()).setNickname(nickname).setUpdateDate(new Date()));
    }

    public void resetPwd(XManagerModel model) {
        String oldPwd = model.getOldPwd();
        String newPwd = model.getNewPwd();

        if (StringUtils.isBlank(oldPwd))
            throw new CustException("旧密码必须输入");
        if (StringUtils.isBlank(newPwd))
            throw new CustException("新密码必须输入");
        XManagerPojo manager = xManagerDao.getRecordByKey(new XManagerPojo().setId(LoginUser.getId()));
        if (manager == null)
            throw new CustException("数据异常");
        if (!manager.getPassword().equals(MD5Util.getMD5Str(oldPwd)))
            throw new CustException("旧密码错误");
        xManagerDao.updateRecordByKey(new XManagerPojo().setId(manager.getId()).setPassword(MD5Util.getMD5Str(newPwd)).setUpdateDate(new Date()));
    }
}
