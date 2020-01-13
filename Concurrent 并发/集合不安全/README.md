# 集合不安全

- 多线程访问同一资源情况下，易发生：ConcurrentMotificationException 错误



## 错误场景

- 多线程并发修改资源类 

```java
	public void test() {
		 List<String> list = new ArrayList<String>();
		 
		 for (int i = 0; i < 30; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		 }
	}
```



## Vector 解决

- vector 源码的 add 方法，大量使用了 synchronized 关键字，保证数据强一致性
- ArrayList 存在的原因，vector 牺牲了效率，在单线程情况下使用 ArrayList

```java
	private void vector(int num) {
		List<String> list = new Vector<String>();
		 
		for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		}
	}
```





## Collections.synchronizedList() 解决

- ArrayList、HashSet 分别实现 List、Set 接口，而这两个接口又分别继承自 Collection 接口
- Collections 是 Collections 的工具类，可以对实现了 Collection 的对象进行分装，使其线程安全

```java
	private void collections(int num) {
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		 
		for (int i = 0; i < num; i++) {
			new Thread(() ->  list.add(UUID.randomUUID().toString()), i + "").start();
			list.forEach(System.out::println);
		}
	}
```





## 类图

![继承关系](https://github.com/FishInWater-1999/Java_Notes/blob/master/Concurrent%20%E5%B9%B6%E5%8F%91/%E9%9B%86%E5%90%88%E4%B8%8D%E5%AE%89%E5%85%A8/%E7%BB%A7%E6%89%BF%E5%85%B3%E7%B3%BB.png)





## 最佳方案：CopyOnWrite

- 写时复制，CopyOnWriteArrayList、CopyOnWriteArraySet、ConcurrentHashMap 
- 原理：

