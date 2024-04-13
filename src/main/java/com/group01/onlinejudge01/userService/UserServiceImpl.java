package com.group01.onlinejudge01.userService;

import com.group01.onlinejudge01.mapper.userMapper;
import com.group01.onlinejudge01.pojo.User;
import com.group01.onlinejudge01.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: OJ1
 * @description:
 * @author: Mr.Wang
 * @create: 2024-04-12 23:07
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private userMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public Map register(User user) {
//        其它处理 默认头像 其它变量默认值
        User reguser = userMapper.getUserByNickName(user.getNickname());
        HashMap data = new HashMap<>();
        if (reguser!=null){
            data.put("result","no");
            return data;
        }
        user.setImgUrl("http://usersimg.oss-cn-beijing.aliyuncs.com/c861af53-9f99-4fca-83da-8c4173eb696a.jpg");
        user.setNumSolved(0);
        user.setNumSolved(0);
        user.setRegistDate(new Date().toString());
        int i=userMapper.insertUser(user);
        String result =i>0?"yes":"no";
        data.put("result",result);
        return data;
    }

    /**
     * 登录
     * @param user
     * @return
     */

    @Override
    public Map login(User user) {

        User loginUser = userMapper.getUserByNickName(user.getNickname());
        HashMap data = new HashMap<>();
        if (loginUser==null||user.getPassword()!=loginUser.getPassword()){
            data.put("result","no");
            return data;
        }
        String token = jwtHelper.createToken(Long.valueOf(loginUser.getUserId()));
        data.put("result","yes");
        data.put("token",token);
        return data;
    }

    /**
     * 校验用户名是否可用
     * @param nickname
     * @return
     */
    @Override
    public Map checkUserName(String nickname) {
        User user=userMapper.getUserByNickName(nickname);
        HashMap data = new HashMap<>();
        if (user==null){
            data.put("result","yes");
            return data;
        }
        data.put("result","no");
        return data;
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */

    @Override
    public Map getUserInfo(String token) {
        HashMap data = new HashMap<>();
        if (jwtHelper.isExpiration(token)) {
            data.put("result","no");
            return data;
        }

        //2.获取token对应的用户
        int userId = jwtHelper.getUserId(token).intValue();

        //3.查询数据
        User user = userMapper.getUserById(userId);

        if (user != null) {
            user.setPassword(0);
            data.put("loginUser",user);
            data.put("result","yes");
            return data;
        }
        data.put("result","no");
        return data;
    }

    /**
     * 修改用户信息
     * @param user
     * @param token
     * @return
     */

    @Override
    public Map updateUser(User user,String token) {
        HashMap data = new HashMap<>();

        if (jwtHelper.isExpiration(token)){
            data.put("result","no");
            return data;
        }
        Long userId = jwtHelper.getUserId(token);
        User updatename = userMapper.getUserByNickName(user.getNickname());
        if (updatename==null){
            user.setUserId(Math.toIntExact(userId));
            int i=userMapper.updateUser(user);
            String result =i>0?"yes":"no";
            data.put("result",result);
            return data;
        }
       data.put("result","no");
        return data;
    }
}
