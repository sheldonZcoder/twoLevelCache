package com.sheldon.twolevelcache.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sheldonzhao
 *
 */
public class ServiceImpl implements Service {

	Map<String, Object> data = new HashMap<String, Object>();

	@Override
	public Object get(String key) {		
		return data.get(key);
	}
	
	@Override
	public void put(String key, Object value) {
		data.put(key, value);
	}
}
