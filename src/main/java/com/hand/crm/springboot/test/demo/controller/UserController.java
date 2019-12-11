package com.hand.crm.springboot.test.demo.controller;

import com.hand.crm.springboot.test.demo.bean.Users;
import com.hand.crm.springboot.test.demo.service.UserService;
import com.hand.crm.springboot.test.demo.util.JsonUtils;
import com.hand.crm.springboot.test.demo.util.RedisClient;
import com.hand.crm.springboot.test.demo.util.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TokenService tokenService;

    @Autowired
    RedisClient redisClient;

    //打印日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value = "/api/getUser")
    public HashMap<String,Object> setUserInfoGet(Users users ,HttpServletResponse response){
        //String name = stringRedisTemplate.opsForValue().get("name");
        //String token = tokenService.getToken(users);
        String name = redisClient.getRedis("name");
        String u = stringRedisTemplate.opsForValue().get("users:u");
        JsonUtils jsonUtils = new JsonUtils();
        Users us = jsonUtils.StringZObj(u,Users.class);
        HashMap<String,Object> result = new HashMap<>();
        try {
            //redisClient.setRedis("token",token);
            stringRedisTemplate.expire("token", 60*60*1000, TimeUnit.SECONDS);
            result.put("result", userService.getUserInfo(users));
            result.put("success", true);
            result.put("redis",name);
            result.put("users",us);
            //Cookie cookie = new Cookie("token", token);
            //cookie.setPath("/");
            //response.addCookie(cookie);
            //result.put("token", token);
        }catch (Exception e){
            result.put("msg",e.getMessage());
            result.put("success",false);
        }finally {
            return result;
        }
    }

    @GetMapping(value = "/demo/getUserToken")
    public HashMap<String,Object> getUserToken(Users users,HttpServletResponse response){
        HashMap<String,Object> result = new HashMap<>();
        try {
            String token = tokenService.getToken(users);
            redisClient.setRedis("token",token);
            stringRedisTemplate.expire("token", 60*60*1000, TimeUnit.SECONDS);
            result.put("result", userService.getUserInfo(users));
            result.put("success", true);
            result.put("token",token);
            //result.put("redis",name);
            //.put("users",us);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            //result.put("token", token);
        }catch (Exception e){
            result.put("msg",e.getMessage());
            result.put("success",false);
        }finally {
            return result;
        }
    }

    @GetMapping(value = "/demo/setRedis")
    public void setRedis(){
        stringRedisTemplate.opsForValue().set("sname","lyl");
    }

    @PostMapping(value = "/demo/setUser")
    public HashMap<String,Object> setUserInfoPost(@RequestBody Users users){
        JsonUtils jsonUtils = new JsonUtils();
        String u = jsonUtils.objZString(users);
        stringRedisTemplate.opsForValue().set("users:u",u);
        return userService.insertUser(users);
    }
//
//    @PutMapping(value = "/demo/setUser")
//    public HashMap<String,Object> setUserInfoPut(@RequestBody Users users){
//        HashMap<String,Object> result = new HashMap<>();
//        try {
//            result.put("result", users);
//            result.put("success", true);
//        }catch (Exception e){
//            result.put("msg",e.getMessage());
//            result.put("success",false);
//        }finally {
//            return result;
//        }
//    }
//
//    @DeleteMapping(value = "/demo/setUser")
//    public HashMap<String,Object> setUserInfoDel(@RequestBody Users users){
//        HashMap<String,Object> result = new HashMap<>();
//        try {
//            result.put("result", users);
//            result.put("success", true);
//        }catch (Exception e){
//            result.put("msg",e.getMessage());
//            result.put("success",false);
//        }finally {
//            return result;
//        }
//    }
}
