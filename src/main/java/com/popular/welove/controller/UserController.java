package com.popular.welove.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.popular.welove.annotation.AuthIgnore;
import com.popular.welove.common.ObjectRestResponse;
import com.popular.welove.common.RspUtils;
import com.popular.welove.entity.User;
import com.popular.welove.service.TokenManagerService;
import com.popular.welove.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
    @Autowired
    protected TokenManagerService tokenManagerService;

    @AuthIgnore
    @PostMapping("/add")
    @ApiOperation(value ="", notes = "添加用户详情")
    public ObjectRestResponse add(@RequestBody User user){
        if(userService.insertOrUpdate(user)){
            return RspUtils.successMsg("插入成功");
        }else {
            return RspUtils.error("插入失败");
        }
    }


    @RequestMapping(value = "getUserByToken", method = RequestMethod.GET)
    @ApiOperation(value = "token获取用户信息", notes = "token获取用户信息")
    @ApiResponses({@ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 400, message = "请输入必传项")})
    public ObjectRestResponse getUserByToken(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam(name = "token", value = "token", required = true) @RequestParam(required = true) String token) {
        LOGGER.info("获取用户");
        User user = tokenManagerService.getUserInfoByToken(token);
        return RspUtils.success(user);

    }

    @AuthIgnore
    @ResponseBody
    @RequestMapping(value = "phoneLogin", method = RequestMethod.POST)
    @ApiOperation(value = "手机验证码登录", notes = "手机验证码登录")
    @ApiResponses({@ApiResponse(code = 200, message = "登录成功"), @ApiResponse(code = 400, message = "请输入必传项")})
    public ObjectRestResponse<User> login(HttpServletRequest request, HttpServletResponse response,
                                            @ApiParam(name = "phone", value = "手机号", required = true) @RequestParam String phone,
                                            @ApiParam(name = "password", value = "验证码", required = true) @RequestParam String password) {
        try {
            User user = userService.selectOne(new EntityWrapper<User>().eq("phone",phone).eq("password",password));
            if(user==null){
                return RspUtils.error("无此用户");
            }else{
                user.setToken(tokenManagerService.getToken(user));
                return RspUtils.successMsg("chegg");
            }
        } catch (Exception e) {
            return RspUtils.error("登陆失败");
        }
    }
}

