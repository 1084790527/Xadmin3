package com.yao.controller;
/**
 * @author 妖妖
 * @date 14:51 2021/3/6
 */

import com.yao.bean.model.XManagerModel;
import com.yao.bean.model.tab.XManagerTab;
import com.yao.bean.vo.ResObj;
import com.yao.service.ManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "manager")
@RestController
public class ManagerController {
    private static Log log = LogFactory.getLog(ManagerController.class);

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResObj managerData(XManagerTab tab){
        return managerService.managerData(tab);
    }

    @GetMapping(value = "add")
    public ResObj addData(){
        return managerService.addData();
    }

    @PostMapping(value = "add")
    public ResObj add(XManagerModel model){
        managerService.add(model);
        return new ResObj().setState(true);
    }

    @GetMapping(value = "modify")
    public ResObj modifyData(XManagerModel model){
        return managerService.modifyData(model);
    }
    @PostMapping(value = "modify")
    public ResObj modify(XManagerModel model){
        managerService.modify(model);
        return new ResObj().setState(true);
    }

    @PutMapping
    public ResObj upState(XManagerModel model){
        managerService.upState(model);
        return new ResObj().setState(true);
    }
    @DeleteMapping
    public ResObj delete(XManagerModel model){
        model.setState("2");
        managerService.upState(model);
        return new ResObj().setState(true);
    }
    @PostMapping(value = "resetPass")
    public ResObj resetPass(XManagerModel model){
        managerService.resetPass(model);
        return new ResObj().setState(true);
    }
}
