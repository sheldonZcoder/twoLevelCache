package com.sheldon.twoLevelCache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import com.sheldon.twolevelcache.service.Service;
import com.sheldon.twolevelcache.service.ServiceProxy;

public class TestCache {
	static final String dir= System.getProperty("user.dir") + "/l2Cache/";
	
	/**
	 * test normal data insert and search
	 */
	@Test
	public void testCache() {
		new File(dir).delete();
		Service service = new ServiceProxy(5);
		service.put("A","Dummy string");
		service.put("B","Dummy string2");
		
		assertEquals("Dummy string", service.get("A"));
		assertEquals("Dummy string2", service.get("B"));
	}

	/**
	 * test update operation with existing key
	 */
	@Test
	public void testCacheUpdate() {
		new File(dir).delete();
		Service service = new ServiceProxy(5);
		service.put("A","Dummy string");
		service.put("B","Dummy string2");
		service.put("B","Dummy string3");
		
		assertEquals("Dummy string", service.get("A"));
		assertEquals("Dummy string3", service.get("B"));
	}
	
	/**
	 * test if our l1 cache is full, is the l2 cache will be able to carry on the job
	 */
	@Test
	public void testCacheFull() {
		new File(dir).delete();
		Service service = new ServiceProxy(2);
		service.put("A","Dummy string");
		service.put("B","Dummy string2");
		service.put("C","Dummy string3");

		assertEquals("Dummy string", service.get("A"));
		assertEquals("Dummy string2", service.get("B"));
		assertEquals("Dummy string3", service.get("C"));
	}
	
	/**
	 * test for not existing key-value pair
	 */
	@Test
	public void testCacheNull() {
		new File(dir).delete();
		Service service = new ServiceProxy(2);
		service.put("A","Dummy string");
		service.put("B","Dummy string2");
		service.put("C","Dummy string3");

		assertEquals("Dummy string", service.get("A"));
		assertEquals("Dummy string2", service.get("B"));
		assertEquals(null, service.get("D"));
	}
}

