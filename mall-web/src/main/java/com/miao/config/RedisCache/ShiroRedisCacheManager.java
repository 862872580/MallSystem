package com.miao.config.RedisCache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * shiro扩展使用redis管理缓存
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {

    @Resource(name = "redisTemplate0")
    private RedisTemplate redisTemplate;

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplate, name);
    }
}
