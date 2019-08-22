package com.sheldon.twolevelcache.service;

public interface Service {
	Object get(String key);
	void put(String key, Object value);
}
