package com.popular.welove.controller;


import com.popular.welove.common.ObjectRestResponse;
import com.popular.welove.common.RspUtils;
import com.popular.welove.entity.User;
import com.popular.welove.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhang
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user" ,tags = "用户")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation(value ="", notes = "查询详情")
    public ObjectRestResponse add(@RequestBody User user){
        if(userService.insertOrUpdate(user)){
            return RspUtils.successMsg("插入成功");
        }else {
            return RspUtils.error("插入失败");
        }
    }
}

