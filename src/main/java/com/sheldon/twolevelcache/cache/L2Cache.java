package com.sheldon.twolevelcache.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * this is the l2 cache which stored as a file
 * @author sheldonzhao
 *
 */
public class L2Cache {
	 private static final String dir= System.getProperty("user.dir") + "/l2Cache/";
	 
	 public L2Cache() {
		 new File(dir).mkdirs();
	 }
	 
	 /**
	  * this method is used to read data from file
	  * @param key
	  * @return
	  */
	 public Object getFromFile(Object key) {
		 Object oOut = null;
		 FileInputStream fis = null;
		 try {
	            fis = new FileInputStream(dir+key);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            oOut = ois.readObject();
	            ois.close();
	        } catch (Exception ex) {
	        	System.out.println("Can't find requesting data");
	        }
	        return oOut;
	 }
	 
	 /**
	  * this method is used to write data to file
	  * @param node
	  */
	 public void writeToFile(DLinkedList node) {
	        try {
	            FileOutputStream fos = new FileOutputStream(dir + node.getKey());
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(node.getValue());
	            oos.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	 }
}
