package com.ganster.cms.web.cache.impl;

import com.ganster.cms.web.cache.CmsCache;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapCache<K,V> implements CmsCache<K,V>{
    private ConcurrentHashMap<K,V> cache = new ConcurrentHashMap<>();
    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V put(K key, V val) {
        return cache.put(key,val);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
