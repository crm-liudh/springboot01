package com.hand.crm.springboot.test.demo.controller;

import com.hand.crm.springboot.test.demo.bean.Users;
import com.hand.crm.springboot.test.demo.service.UserService;
import com.hand.crm.springboot.test.demo.util.JsonUtils;
import com.hand.crm.springboot.test.demo.util.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisClient redisClient;

    //打印日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value = "/demo/getUser")
    public HashMap<String,Object> setUserInfoGet(Users users){
        //String name = stringRedisTemplate.opsForValue().get("name");
        String name = redisClient.getRedis("name");
        String u = stringRedisTemplate.opsForValue().get("users:u");
        JsonUtils jsonUtils = new JsonUtils();
        Users us = jsonUtils.StringZObj(u,Users.class);
        HashMap<String,Object> result = new HashMap<>();
        try {
            result.put("result", userService.getUserInfo(users));
            result.put("success", true);
            result.put("redis",name);
            result.put("users",us);
        }catch (Exception e){
            result.put("msg",e.getMessage());
            result.put("success",false);
        }finally {
            return result;
        }
    }

    @GetMapping(value = "/demo/setRedis")
    public void setRedis(){
        stringRedisTemplate.opsForValue().set("pname","lyl");
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
