package com.cn.xt.mp.base.redis.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
   public  static RedisTemplate redisTemplate;

    private static long l = 30;//过期时长

    @Autowired
    public  void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static void set(Object K, Object V){
        redisTemplate.opsForValue().set(K,V);
    }

    public static void set(Object K,Object V,long offset){
        redisTemplate.opsForValue().set(K,V,offset);
    }

    public static void set(Object K, Object V, long l, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(K,V,l,timeUnit);
    }
    public static void cacheSet(Object K, Object V){
        redisTemplate.opsForValue().set(K,V,l,TimeUnit.MINUTES);
    }

    public static Object get(Object K){
       return redisTemplate.opsForValue().get(K);
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public static boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    public static void delete(Collection<String> key) {
        redisTemplate.delete(key);
    }

}
