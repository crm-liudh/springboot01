package com.hand.crm.springboot.test.demo.controller;

import com.hand.crm.springboot.test.demo.bean.Users;
import com.hand.crm.springboot.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/demo/getUser")
    public HashMap<String,Object> setUserInfoGet(Users users){
        HashMap<String,Object> result = new HashMap<>();
        try {
            result.put("result", userService.getUserInfo(users));
            result.put("success", true);
        }catch (Exception e){
            result.put("msg",e.getMessage());
            result.put("success",false);
        }finally {
            return result;
        }
    }

    @PostMapping(value = "/demo/setUser")
    public HashMap<String,Object> setUserInfoPost(@RequestBody Users users){
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
