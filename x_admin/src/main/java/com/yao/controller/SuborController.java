package com.yao.controller;
/**
 * @author 妖妖
 * @date 11:18 2021/3/5
 */

import com.yao.bean.model.XServiceModel;
import com.yao.bean.model.tab.XServiceTab;
import com.yao.bean.vo.ResObj;
import com.yao.service.SuborService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "subor")
@RestController
public class SuborController {
    private static Log log = LogFactory.getLog(SuborController.class);

    @Autowired
    private SuborService suborService;

    @GetMapping
    public ResObj suborData(XServiceTab tab){
        return suborService.suborData(tab);
    }

    @PutMapping
    public ResObj suborState(XServiceModel model){
        suborService.suborState(model);
        return new ResObj().setState(true);
    }

    @GetMapping(value = "add")
    public ResObj addData(){
        return suborService.addData();
    }
}
