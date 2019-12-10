package com.hand.crm.springboot.test.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisClient {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //设置key-value到redis中
    public boolean setRedis(String key, String value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 功能描述：通过key获取redis缓存
     * @param key
     * @return
     */
    public String getRedis(String key){

        return redisTemplate.opsForValue().get(key);
    }
}
