package com.sheldon.twolevelcache.cache;
/**
 * here we use a doubly linkedlist as our CacheData and the tool we use to track least recent used DLinkedList
 * @author sheldonzhao
 *
 */
public class DLinkedList {
	private Object key;
	private Object value;
	private DLinkedList pre;
	private DLinkedList next;
	
	public DLinkedList(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public DLinkedList(Object key, Object value, DLinkedList pre, DLinkedList next) {
		this.key = key;
		this.value = value;
		this.pre = pre;
		this.next = next;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public DLinkedList getPre() {
		return pre;
	}

	public void setPre(DLinkedList pre) {
		this.pre = pre;
	}

	public DLinkedList getNext() {
		return next;
	}

	public void setNext(DLinkedList next) {
		this.next = next;
	}	
}
