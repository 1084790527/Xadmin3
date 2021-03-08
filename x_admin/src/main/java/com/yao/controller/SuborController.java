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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "add")
    public ResObj addData(){
        return suborService.addData();
    }
    @PostMapping(value = "add")
    public ResObj add(XServiceModel model){
        suborService.add(model);
        return new ResObj().setState(true);
    }
    @GetMapping(value = "modify")
    public ResObj modifyData(XServiceModel model){
        return suborService.modifyData(model);
    }

    @PostMapping(value = "modify")
    public ResObj modify(XServiceModel model){
        suborService.modify(model);
        return new ResObj().setState(true);
    }
    @PutMapping
    public ResObj upState(XServiceModel model){
        suborService.upState(model);
        return new ResObj().setState(true);
    }
    @DeleteMapping
    public ResObj delete(XServiceModel model){
        model.setState("2");
        suborService.upState(model);
        return new ResObj().setState(true);
    }
    @PostMapping(value = "resetPass")
    public ResObj resetPass(XServiceModel model){
        suborService.resetPass(model);
        return new ResObj().setState(true);
    }
}
