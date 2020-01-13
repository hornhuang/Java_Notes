package juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.Test;

public class SetNotSafe {
	
	@Test
	public void test() {
		 //notSafe(30);
		//vector(30);
//		collections(30);
		copyOnWrite(30);
	}

	private void notSafe(int num) {
		List<String> list = new ArrayList<String>();
		 
		 for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		 }
	}
	
	private void vector(int num) {
		List<String> list = new Vector<String>();
		
		for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		}
	}
	
	private void collections(int num) {
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		 
		for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		}
	}
	
	private void copyOnWrite(int num) {
		List<String> list = new ConcurrentHashMap()<String>();
		 
		for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		}
	}
	
}
