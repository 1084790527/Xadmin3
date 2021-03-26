package com.yao.controller.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 妖妖
 * @date 11:16 2021/3/16
 */

//@ApiModel：用于响应类上，表示一个返回响应数据的信息
//            （这种一般用在post创建的时候，使用@RequestBody这样的场景，
//            请求参数无法使用@ApiImplicitParam注解进行描述的时候）
//    @ApiModelProperty：用在属性上，描述响应类的属性
@ApiModel(description = "请求数据")
public class TestModel {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "年龄")
    private Long age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
