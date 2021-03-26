package com.yao.controller;
/**
 * @author 妖妖
 * @date 14:10 2021/3/3
 */

import com.yao.bean.model.LoginModel;
import com.yao.bean.vo.ResObj;
import com.yao.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "login")
public class LoginController {
    private static Log log = LogFactory.getLog(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResObj login(LoginModel model){
        loginService.login(model);
        return new ResObj().setState(true);
    }
}
