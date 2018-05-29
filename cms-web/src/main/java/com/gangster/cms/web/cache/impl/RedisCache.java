package com.gangster.cms.web.cache.impl;

import com.gangster.cms.web.cache.CmsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
// TODO: 5/29/18 未完成
public class RedisCache<K,V> implements CmsCache<K,V> {

    @Autowired
    RedisTemplate<K,V> redisTemplate;

    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public V put(K key, V val) {
        return redisTemplate.opsForValue().getAndSet(key,val);
    }

    @Override
    public boolean containsKey(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void clear() {
        redisTemplate.discard();
    }
}
