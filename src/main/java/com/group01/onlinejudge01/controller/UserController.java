package com.group01.onlinejudge01.controller;

import com.group01.onlinejudge01.pojo.User;
import com.group01.onlinejudge01.userService.UserService;
import com.group01.onlinejudge01.utils.OSSutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: OJ1
 * @description: 用户业务接口
 * @author: Mr.Wang
 * @create: 2024-04-12 23:01
 **/

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OSSutil ossUtil;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public Map register(@RequestBody User user){
        Map data=userService.register(user);
        return data;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public Map login(@RequestBody User user){
        Map data=userService.login(user);
        return data;
    }

    /**
     * 校验用户名是否可用
     * @param nickname
     * @return
     */

    @PostMapping("checkUserName")
    public Map checkUserId(String nickname){
        Map data = userService.checkUserName(nickname);
        return data;
    }

    /**
     * 根据token获取用户数据
     * @param token
     * @return
     */
    @GetMapping("getUserInfo")
    public Map getUserInfo(@RequestHeader String token){
        Map data=userService.getUserInfo(token);
        return data;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */

    @PostMapping("updateUser")
    public Map updateUser(@RequestBody User user,@RequestHeader String token){
        Map data =userService.updateUser(user,token);
        return data;
    }

    @PostMapping("upload")
    public Map upload(MultipartFile image) throws IOException {
        HashMap data = new HashMap();
        String url = ossUtil.upload(image);
        data.put("url",url);
        return data;
    }

}
