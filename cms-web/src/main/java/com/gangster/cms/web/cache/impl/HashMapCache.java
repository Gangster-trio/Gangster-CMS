package com.gangster.cms.web.cache.impl;

import com.gangster.cms.web.cache.CmsCache;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapCache<K,V> extends ConcurrentHashMap implements CmsCache<K,V>{
}
