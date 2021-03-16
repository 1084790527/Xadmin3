package com.yao.controller;
/**
 * @author 妖妖
 * @date 10:16 2021/3/16
 */

import com.yao.bean.vo.ResObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试接口")
@RestController
@RequestMapping(value = "test")
public class TestApi {
    private static Log log = LogFactory.getLog(TestApi.class);

    @ApiOperation("测试")
    @GetMapping
    public ResObj test() {
        return new ResObj().setState(true);
    }
}
