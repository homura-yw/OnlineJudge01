//package com.group01.onlinejudge01.interceptor;
//
//import com.alibaba.druid.util.StringUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.group01.onlinejudge01.utils.JwtHelper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.HashMap;
//
//@Component
//public class LoginProtectInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtHelper jwtHelper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
//            HashMap data = new HashMap<>();
//            data.put("result","no");
//            ObjectMapper objectMapper = new ObjectMapper();
//            String json = objectMapper.writeValueAsString(data);
//            response.getWriter().print(json);
//            //拦截
//            return false;
//        }else{
//            //放行
//            return true;
//        }
//    }
//}