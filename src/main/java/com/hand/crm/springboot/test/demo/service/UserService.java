package com.hand.crm.springboot.test.demo.service;

import com.hand.crm.springboot.test.demo.bean.Users;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public List<Users> getUserInfo(Users users);

    public HashMap<String,Object> insertUser(Users users);
}
