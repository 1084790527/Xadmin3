package com.yao.controller;
/**
 * @author 妖妖
 * @date 14:35 2021/3/5
 */

import com.yao.bean.model.XRoleModel;
import com.yao.bean.model.tab.XRoleTab;
import com.yao.bean.vo.ResObj;
import com.yao.service.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "role")
@RestController
public class RoleController {
    private static Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResObj roleData(XRoleTab tab){
        return roleService.roleData(tab);
    }

    @GetMapping(value = "add")
    public ResObj addData(){
        return roleService.addData();
    }

    @PostMapping(value = "add")
    public ResObj add(XRoleModel model){
        roleService.add(model);
        return new ResObj().setState(true);
    }
    @GetMapping(value = "modify")
    public ResObj modifyData(XRoleModel model){
        return roleService.modifyData(model);
    }
    @PostMapping(value = "modify")
    public ResObj modify(XRoleModel model){
        roleService.modify(model);
        return new ResObj().setState(true);
    }

    @PutMapping
    public ResObj upState(XRoleModel model){
        roleService.upState(model);
        return new ResObj().setState(true);
    }

    @DeleteMapping
    public ResObj delete(XRoleModel model){
        model.setState("2");
        roleService.upState(model);
        return new ResObj().setState(true);
    }

}
