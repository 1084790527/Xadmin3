package com.yao.controller;
/**
 * @author 妖妖
 * @date 16:12 2021/3/9
 */

import com.yao.bean.model.XManagerModel;
import com.yao.bean.vo.ResObj;
import com.yao.service.InfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "info")
@RestController
public class InfoController {
    private static Log log = LogFactory.getLog(InfoController.class);

    @Autowired
    private InfoService infoService;

    @GetMapping
    public ResObj infoData(){
        return infoService.infoData();
    }

    @PostMapping
    public ResObj info(XManagerModel model){
        infoService.info(model);
        return new ResObj().setState(true);
    }

    @PostMapping(value = "resetPwd")
    public ResObj resetPwd(XManagerModel model){
        infoService.resetPwd(model);
        return new ResObj().setState(true);
    }
}
