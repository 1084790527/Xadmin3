package com.yao.controller;
/**
 * @author 妖妖
 * @date 16:34 2021/3/3
 */

import com.yao.bean.vo.ResObj;
import com.yao.service.exception.CustException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestControllerAdvice
public class ControllerEHA {
    private static Log log = LogFactory.getLog(ControllerEHA.class);

    @ExceptionHandler
    public ResObj handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.info("Restful Http请求发生异常...");
        if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            log.info("修改返回状态值为200");
            res.setStatus(HttpStatus.OK.value());
        }
        if (e instanceof CustException){
            log.error("代码98：" + e.getMessage(), e);
            return new ResObj().setState(false).setCount(0L).setCode(98).setMsg("请求异常 -->> "+e.getMessage());
        }else if (e instanceof NullPointerException) {
            log.error("代码00：" + e.getMessage(), e);
            return new ResObj().setState(false).setCount(0L).setCode(0).setMsg("发生空指针异常 -->> "+e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            log.error("代码01：" + e.getMessage(), e);
            return new ResObj().setState(false).setCount(0L).setCode(1).setMsg("请求参数类型不匹配 -->> "+e.getMessage());
        } else if (e instanceof SQLException) {
            log.error("代码02：" + e.getMessage(), e);
            return new ResObj().setState(false).setCount(0L).setCode(2).setMsg("数据库访问异常 -->> "+e.getMessage());
        } else {
            log.error("代码99：" + e.getMessage(), e);
            return new ResObj().setState(false).setCount(0L).setCode(99).setMsg("服务器代码发生异常,请联系管理员 -->> "+e.getMessage());
        }
    }
}
