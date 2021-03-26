package com.yao.controller.api;
/**
 * @author 妖妖
 * @date 10:16 2021/3/16
 */

import com.yao.bean.vo.ResObj;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api：用在请求的类上，说明该类的作用  tags="说明该类的作用"  value="该参数没什么意义，所以不需要配置"
@Api(tags = "测试接口")
@RestController
@RequestMapping(value = "api")
public class TestController {
    private static Log log = LogFactory.getLog(TestController.class);

    //@ApiOperation："用在请求的方法上，说明方法的作用"  value="说明方法的作用"  notes="方法的备注说明"
    @ApiOperation(value = "测试")
    //@ApiImplicitParams：用在请求的方法上，包含一组参数说明
    //    @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的配置信息
    //        name：参数名
    //        value：参数的汉字说明、解释
    //        required：参数是否必须传
    //        paramType：参数放在哪个地方
    //            · header --> 请求参数的获取：@RequestHeader
    //            · query --> 请求参数的获取：@RequestParam
    //            · path（用于restful接口）--> 请求参数的获取：@PathVariable
    //            · body（不常用）
    //            · form（不常用）
    //        dataType：参数类型，默认String，其它值dataType="Integer"
    //        defaultValue：参数的默认值
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "form"),
            @ApiImplicitParam(name = "state",value = "状态",required = true,paramType = "form"),
            @ApiImplicitParam(name = "name",value = "姓名",required = false,paramType = "form"),
            @ApiImplicitParam(name = "sex",value = "性别",required = false,paramType = "form"),
            @ApiImplicitParam(name = "age",value = "年龄",required = true,paramType = "form")
    })
    //@ApiResponses：用于请求的方法上，表示一组响应
    //    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
    //        code：数字，例如400
    //        message：信息，例如"请求参数没填好"
    //        response：抛出异常的类
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @GetMapping
    public ResObj test(TestModel model) {
        return new ResObj().setState(true);
    }
}
