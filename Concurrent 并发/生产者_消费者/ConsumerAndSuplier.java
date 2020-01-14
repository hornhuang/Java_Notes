package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

class Cake {
	
	private int num = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition_1 = lock.newCondition();
	private Condition condition_2 = lock.newCondition();
	
	public void order() throws Exception {
		lock.lock();
		try {
			// ÅÐ¶Ï
			while (num != 0) {
				condition_1.await();
			}
			// Ö´ÐÐ
			num++;
			System.out.println(num);
			// »½ÐÑ
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
			// ÅÐ¶Ï
			while (num != 1) {
				condition_1.await();
			}
			// Ö´ÐÐ
			num++;
			System.out.println(num);
			// »½ÐÑ
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
			// ÅÐ¶Ï
			while (num != 2) {
				condition_2.await();
			}
			// Ö´ÐÐ
			num-=2;
			System.out.println(num);
			// »½ÐÑ
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
					cake.order();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

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
