package com.yao.controller;
/**
 * @author 妖妖
 * @date 17:19 2021/3/2
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping
@Controller
public class BaseController {
    private static Log log = LogFactory.getLog(BaseController.class);

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String main(){
        return "redirect:/index";
    }

    /**
     * 全匹配请求url排除static静态路径
     * @return 映射页面
     */
//    @GetMapping(value = {"{path:\\b(?!static\\b)\\w+}/**"})
    @GetMapping(value = {"{path:(?!static\\b)\\w+}/**"})
    public String path(){
        return request.getServletPath();
    }
}
