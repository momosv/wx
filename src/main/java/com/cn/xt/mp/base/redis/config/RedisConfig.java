
package com.cn.xt.mp.base.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    public KeyGenerator KeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, @Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30));

        //设置默认超过期时间是30分
        defaultCacheConfig.entryTtl(Duration.ofMinutes(30));
        defaultCacheConfig.getTtl();
  //      defaultCacheConfig.disableCachingNullValues();     // 不缓存空值
        //初始化RedisCacheManager

        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        //使用fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);

        //kryo
        //KryoRedisSerializer kryoRedisSerializer = new KryoRedisSerializer(Object.class);
        // value值的序列化采用kryoRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        ParserConfig.getGlobalInstance().addAccept("com.cn.xt.mp");
       // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        template.setConnectionFactory(redisConnectionFactory);
    //    RedisUtil.redisTemplate = template;
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(@Qualifier("redisTemplate")RedisTemplate redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(@Qualifier("redisTemplate")RedisTemplate redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(@Qualifier("redisTemplate")RedisTemplate redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(@Qualifier("redisTemplate")RedisTemplate redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(@Qualifier("redisTemplate")RedisTemplate redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
