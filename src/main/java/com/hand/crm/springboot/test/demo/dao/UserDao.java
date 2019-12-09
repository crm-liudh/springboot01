package com.hand.crm.springboot.test.demo.dao;

import com.hand.crm.springboot.test.demo.bean.Users;

import java.util.List;

public interface UserDao {
    public List<Users> getUserInfo(Users users);

    public int insertUser(Users users);
}
