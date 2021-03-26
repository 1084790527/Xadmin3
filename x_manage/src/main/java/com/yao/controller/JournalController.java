package com.yao.controller;
/**
 * @author 妖妖
 * @date 15:36 2021/3/15
 */

import com.yao.bean.model.tab.XSystemLogTab;
import com.yao.bean.vo.ResObj;
import com.yao.service.AopService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "journal")
@RestController
public class JournalController {
    private static Log log = LogFactory.getLog(JournalController.class);

    @Autowired
    private AopService aopService;

    @GetMapping
    public ResObj journalData(XSystemLogTab tab){
        return aopService.journalData(tab);
    }
}
