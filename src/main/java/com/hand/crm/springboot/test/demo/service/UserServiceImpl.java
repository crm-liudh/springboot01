package com.hand.crm.springboot.test.demo.service;

import com.hand.crm.springboot.test.demo.bean.Users;
import com.hand.crm.springboot.test.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Users> getUserInfo(Users users) {
        return userDao.getUserInfo(users);
    }

    @Override
    public HashMap<String,Object> insertUser(Users users) {
        HashMap<String,Object> result = new HashMap<>();
        try {
            result.put("result", userDao.insertUser(users));
            result.put("success", true);
        }catch (Exception e){
                result.put("msg",e.getMessage());
                result.put("success",false);
            }finally {
                return result;
            }
    }
}
