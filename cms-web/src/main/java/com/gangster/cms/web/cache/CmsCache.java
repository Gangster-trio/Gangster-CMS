package com.gangster.cms.web.cache;

public interface CmsCache<K, V> {
    //读取缓存，找不到返回null
    V get(K key);

    V put(K key, V val);

    boolean containsKey(K key);

    //清除全部缓存
    void clear();
}
