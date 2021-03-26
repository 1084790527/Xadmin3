package com.yao.controller;
/**
 * @author 妖妖
 * @date 16:10 2021/3/3
 */

import com.yao.bean.vo.ResObj;
import com.yao.service.AuthorityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "menu")
@RestController
public class MenuController {
    private static Log log = LogFactory.getLog(MenuController.class);

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public ResObj menu(){
        return authorityService.getTreeMenu();
    }

}
