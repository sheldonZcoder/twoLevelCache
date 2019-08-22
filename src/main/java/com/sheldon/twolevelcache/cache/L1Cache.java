package com.sheldon.twolevelcache.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * The L1 cache is a LRU cache
 * @author sheldonzhao
 *
 */
public class L1Cache {
	 	
	 	L2Cache cacheFile;
	    //the reason we use a map structure is that 
	 	//we want to achieve constant time complexity for both put and get operation
	    Map<Object, DLinkedList> map;
	    //the reason we use a pesudo head and tail is that we want to mark the boundary so that we do't have to check 
	    //node when we add or remove a DlinkedList
	    DLinkedList head = new DLinkedList("Dummy head", 0);
	    DLinkedList tail = new DLinkedList("Dummy Tail", 0);
	    //this is the current size of our cache
	    int size;
	    //this is the max size of our cache
	    int capacity;
	    
	    public L1Cache(int capacity) {
	        map = new HashMap<Object, DLinkedList>();
	        cacheFile = new L2Cache();
	        this.capacity = capacity;
	        head.setNext(tail);
	        tail.setPre(head);
	    }
	    
	    public void addNode(DLinkedList list) {
	        //always add node to position next to head
	    	list.setNext(head.getNext());
	    	list.getNext().setPre(list);
	    	head.setNext(list);
	    	list.setPre(head);
	    }
	    
	    public void removeNode(DLinkedList list) {
	    	list.getPre().setNext(list.getNext());
	    	list.getNext().setPre(list.getPre());
	    }
	    
	    public DLinkedList removeTail() {
	        //remove the DLinkedlist right before tail
	        DLinkedList temp = tail.getPre();
	        temp.getNext().setPre(temp.getPre());
	        temp.getPre().setNext(temp.getNext());
	        
	        return temp;
	    }
	    
	    public void moveToHead(DLinkedList list) {
	        removeNode(list);
	        addNode(list);
	        
	    }
	    
	    //if we can't find key in our map, then try L2Cache
	    //if we still can't find it
	    //then we return null to indicate that there's no such record in our cache
	    public Object get(Object key) {
	        if (map.containsKey(key)) {
	            moveToHead(map.get(key));
	            return map.get(key).getValue();
	        } else {
	        	Object obj = cacheFile.getFromFile(key);
	        	if (obj != null) {
		        	DLinkedList temp = new DLinkedList(key, obj);
		        	addNode(temp);
		        	DLinkedList LRU = removeTail();
		        	cacheFile.writeToFile(LRU);
	        	}
	        	return obj;
	        }
	    }
	    
	    public void put(Object key, Object value) {
	    	//if we already have the key, then we update the old value to new value
	    	//then move the currently accessed DLinkedList to the position next to head
	    	//otherwise, we create a new DLinkedList and add it the position next to head
	    	//if our cache already reach max capacity, then we have to move the least recent use DLinkedList to L2Cache
	        if (map.containsKey(key)) {
	            DLinkedList list = map.get(key);
	            list.setKey(key);
	            list.setValue(value);
	            moveToHead(list);
	        } else {
	            DLinkedList list = new DLinkedList(key, value);
	            map.put(key, list);
	            addNode(list);
	            size += 1;
	            
	            if (size > capacity) {
	                DLinkedList LRU = removeTail();
	                cacheFile.writeToFile(LRU);
	                map.remove(LRU.getKey());
	                size -= 1;
	            }
	        }
	    }
}
