# 生产者 消费者

- 并发编程
- 在高内聚低耦合前提下，线程操作资源类
- 判断 - 执行 - 唤醒
- 避免虚假唤醒

 

## Java 7 之前版本

- 蛋糕 cake 资源类

```java
class Cake {
	
	private int num = 0;
	
	public synchronized void make() throws Exception {
		// 判断
		while (num != 0) {
			this.wait();
		}
		// 执行
		num++;
		System.out.println(num);
		// 唤醒
		this.notifyAll();
	}
	
	public synchronized void sell() throws Exception {
		// 判断
		while (num == 0) {
			this.wait();
		}
		// 执行
		num--;
		System.out.println(num);
		// 唤醒
		this.notifyAll();
	}
	
	public int getNum() {
		return num;
	}
	
}
```

- 操作资源类

```java
public class ConsumerAndSuplier {

	@Test
	public void test1() {
		Cake cake = new Cake();
		for (int i = 0; i < 10; i++) {
			work(cake);
		}
	}

	private void work(Cake cake) {
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				try {
					cake.make();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				try {
					cake.sell();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
```

- 注意：根据 JDK 文档，这里使用 while (...) {this.wait();} 进行判断，防止虚假唤醒，保证生成一个消费一个 1010...



## Java 8 开始

- 使用 ReentrantLock 可重入的互斥锁，分公平和不公平两种情况

```java
class Cake {
	
	private int num = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void make() throws Exception {
		lock.lock();
		try {
			// 判断
			while (num != 0) {
				condition.await();
			}
			// 执行
			num++;
			System.out.println(num);
			// 唤醒
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void sell() throws Exception {
		lock.lock();
		try {
			// 判断
			while (num == 0) {
				condition.await();
			}
			// 执行
			num--;
			System.out.println(num);
			// 唤醒
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public int getNum() {
		return num;
	}
	
}
```

- 首先是基本的 Lock 框架 lock.lock(); try {} catch( Exception e ) {} finally { lock.unlock() }
- 用 condition 的 await() 和 signalAll() 方法替代 synchronize 内部锁的方法
- lock 可以创造多个 condition 

```java
class Cake {
	
	private int num = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition_1 = lock.newCondition();
	private Condition condition_2 = lock.newCondition();
	
	public void order() throws Exception {
		lock.lock();
		try {
			// 判断
			while (num != 0) {
				condition_1.await();
			}
			// 执行
			num++;
			System.out.println(num);
			// 唤醒
			condition_1.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void make() throws Exception {
		lock.lock();
		try {
			// 判断
			while (num != 1) {
				condition_1.await();
			}
			// 执行
			num++;
			System.out.println(num);
			// 唤醒
			condition_2.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void sell() throws Exception {
		lock.lock();
		try {
			// 判断
			while (num != 2) {
				condition_2.await();
			}
			// 执行
			num-=2;
			System.out.println(num);
			// 唤醒
			condition_1.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public int getNum() {
		return num;
	}
	
}
```

- 多把锁实现精确控制

