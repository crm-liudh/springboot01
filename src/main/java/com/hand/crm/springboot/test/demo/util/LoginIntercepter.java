package com.hand.crm.springboot.test.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Autowired
    RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("preHandle");
        String sign = redisClient.getRedis("token");
        if(token.equals(sign))
            return HandlerInterceptor.super.preHandle(request,response,handler);
        else return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
