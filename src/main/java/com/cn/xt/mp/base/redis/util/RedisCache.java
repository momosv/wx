package com.cn.xt.mp.base.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Set;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCache() {
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return this.redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object get(Object key) {
        final String keyf = key.toString();
        Object object = null;
        object = this.redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                return value == null ? null : RedisCache.this.toObject(value);
            }
        });
        return object;
    }

    public void put(Object key, final Object value, final long liveTime) {
        final String keyf = key.toString();
        this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyf.getBytes();
                byte[] valueb = RedisCache.this.toByteArray(value);
                connection.set(keyb, valueb);
                if (liveTime > 0L) {
                    connection.expire(keyb, liveTime);
                }

                return 1L;
            }
        });
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        }

        return obj;
    }

    public void del(Object key) {
        final String keyf = key.toString();
        this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(new byte[][]{keyf.getBytes()});
            }
        });
    }

    public void exprie(Object key, final long liveTime) {
        final String keyf = key.toString();
        this.redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.expire(keyf.getBytes(), liveTime);
            }
        });
    }

    public Boolean exist(Object key) {
        final String keyf = key.toString();
        return (Boolean)this.redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(keyf.getBytes());
            }
        });
    }

    public void clear() {
        this.redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    public Long incr(Object key) {
        final String keyf = key.toString();
        return (Long)this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(keyf.getBytes());
            }
        });
    }

    public Long decr(Object key) {
        final String keyf = key.toString();
        return (Long)this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.decr(keyf.getBytes());
            }
        });
    }

    public Set<String> keys(String key) {
        Set<String> keys = this.redisTemplate.keys(key);
        return keys;
    }
}
