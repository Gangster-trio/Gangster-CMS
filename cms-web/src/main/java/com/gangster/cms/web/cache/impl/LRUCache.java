package com.gangster.cms.web.cache.impl;

import com.gangster.cms.web.cache.CmsCache;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> extends LinkedHashMap<K,V> implements CmsCache<K, V> {
    private Lock lock = new ReentrantLock();
    private int cacheSize;
    private final int  defaultSize = 128;

    public LRUCache(int cacheSize){
        super();
        this.cacheSize = cacheSize;

    }

    public LRUCache(){
        super();
        this.cacheSize = defaultSize;
    }

    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        }finally {
            lock.unlock();
        }
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}
