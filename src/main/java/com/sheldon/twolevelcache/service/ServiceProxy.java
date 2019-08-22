package com.sheldon.twolevelcache.service;

import com.sheldon.twolevelcache.cache.L1Cache;
import com.sheldon.twolevelcache.service.Service;
import com.sheldon.twolevelcache.service.ServiceImpl;

/**
 * this is my service proxy
 * by using ServiceProxy, we add to both the data access service and our cache, also can read on both
 * @author sheldonzhao
 *
 */
public class ServiceProxy implements Service {

	private Service service;
	private L1Cache cache;
	
	public ServiceProxy(int capacity) {
		service = new ServiceImpl();
		cache = new L1Cache(capacity);
	}
	
	@Override
	public Object get(String key) {
		Object obj = cache.get(key);
		if (obj == null) {
			return service.get(key);
		}
		return obj;
	}

	@Override
	public void put(String key, Object value) {
		service.put(key, value);
		cache.put(key, value);
	}
	
	
	
}
