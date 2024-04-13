package com.group01.onlinejudge01.userService;

import com.group01.onlinejudge01.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: OJ1
 * @description:
 * @author: Mr.Wang
 * @create: 2024-04-12 23:05
 **/
public interface UserService {

    Map register(User user);

    Map login(User user);

    Map checkUserName(String nickname);

    Map getUserInfo(String token);

    Map updateUser(User user,String token);
}
